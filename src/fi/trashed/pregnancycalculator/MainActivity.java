package fi.trashed.pregnancycalculator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener 
{
	public static final String MY_PREFS = "settings";
	//private String thisDate;
	
	// Visible content
	Button quitButton;
	Button resetButton;
	Button insertButton;
	
	TextView currentDate;
	TextView dateOfBirth;
	TextView elapsedPregnancyTime;
	
	SimpleDateFormat sdf;
	
	Handler mHandler;
	Runnable Refresh;
	
	private Date birthdate;
	// Calendar
	Calendar calendar;
	
	public static Context context;
	
	private static SharedPreferences mySettings;
	private static SharedPreferences.Editor editor;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        
        
        mySettings = getSharedPreferences(MY_PREFS, 0);
        editor = mySettings.edit();
        /*editor.clear();
        editor.commit();*/
        
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        //thisDate = (sdf).format(new Date());
        
        if (!mySettings.getBoolean("settingsSaved", false)) {
        	Intent setBirthDate = new Intent(this, SetBirthDateActivity.class);
        	startActivity(setBirthDate);
        }
        
        setContentView(R.layout.main);
        
    }
    
    
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	
    	context = this;
    	
    	//mySettings = getSharedPreferences(MY_PREFS, 0);
    	//setContentView(R.layout.main);
    	
    	quitButton = (Button)findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this);
        resetButton = (Button)findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        insertButton = (Button)findViewById(R.id.insert_button);
        insertButton.setOnClickListener(this);
        
        currentDate = (TextView)findViewById(R.id.currentDateField);
        //currentDate.setText(date);    	
    	
    	//this.currentDate.setText(date);
    	dateOfBirth = (TextView)findViewById(R.id.birthDateField);
    	//dateOfBirth.setText(birthDate);
    	elapsedPregnancyTime = (TextView)findViewById(R.id.elapsedWeeks);
    	
    	updateTextViews();
    }
    
    /** Function to perform button clicks.
     * @param: View v
     */
	@Override
	public void onClick(View v) 
	{
		if (v == quitButton) {
			finish();
		}
		else if (v == insertButton) {
			Intent setBirthDate = new Intent(this, SetBirthDateActivity.class);
        	startActivity(setBirthDate);
		}
		else if (v == resetButton) {
			editor.clear();
			editor.commit();
			//updateTextViews();
			reset();
		}
	} 
	
	/** Clear the birth date field
	 * @author Jose
	 */
	private void reset() 
	{
		dateOfBirth.setText("-/-/-");
		elapsedPregnancyTime.setText("0w + 0d");	
		birthdate = null;
	}

	/** Update TextViews 
	 * @author Jose
	 */
	private void updateTextViews()
	{
		currentDate.setText((sdf).format(new Date()));
		
		dateOfBirth.setText(getBirthDate());
		
		// Get this date;
		if (birthdate != null) {
			//CalculateWeeks calculator = new CalculateWeeks(new Date().getTime(), birthdate.getTime());
			
			Calendar newCalendar = Calendar.getInstance();
			newCalendar.setTime(birthdate);
			CalculateWeeks calculator = new CalculateWeeks(calendar, newCalendar);
			
			elapsedPregnancyTime.setText(calculator.getElapsedTime());
		}
		
		
	}
	
	/** Get birth date from SharedPreferences
	 * @author Jose
	 * @return birthDate
	 */
	private String getBirthDate()
	{
		int day = mySettings.getInt("dayOfBirth", 0);
		int month = mySettings.getInt("monthOfBirth", 0);
		int year = mySettings.getInt("yearOfBirth", 0);
		
		String date;
		
		//Calendar cal = Calendar.getInstance();
		if (day == 0 && month == 0 && year == 0) {
			date = "-/-/-";
		}
		else {
			birthdate = new Date(year - 1900, month, day);
			date = (sdf).format(birthdate);
		}
		
		return date;
	}
}