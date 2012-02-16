package fi.trashed.pregnancycalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class SetBirthDateActivity extends Activity implements OnClickListener 
{
	public static final String MY_PREFS = "settings";
	private int day, month, year;
	//private String birthDate = null;
	
	// Visible content
	Button confirmButton;
	DatePicker birthDatePicker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_birth_date_layout);
        
        // Initialize button
        confirmButton = (Button)findViewById(R.id.confirm_button);
        // Set listener
        confirmButton.setOnClickListener(this);
        
        // Initialize DatePicker
        birthDatePicker = (DatePicker)findViewById(R.id.birth_date_picker);
    }
    
    /** Function to handle button click
     * 
     * @param: View v
     */
	@Override
	public void onClick(View v) 
	{
		day = birthDatePicker.getDayOfMonth();
		month = birthDatePicker.getMonth();
		year = birthDatePicker.getYear();
		
		saveSettings();
		
		finish();
		//birthDate = Integer.toString(day) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
	}
	
	/** Save settings to shared preferences
	 * 
	 */
	private void saveSettings()
	{
		SharedPreferences settings = getSharedPreferences(MY_PREFS, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("settingsSaved", true);
		editor.putInt("dayOfBirth", day);
		editor.putInt("monthOfBirth", month);
		editor.putInt("yearOfBirth", year);
		editor.commit();
	}
}
