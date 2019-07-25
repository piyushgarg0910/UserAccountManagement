package com.gargpiyush.android.useraccountmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 12:54.
 */
public class EmailValidation {

    public static boolean isEmailValid(CharSequence charSequence)
    {
        String regExpression =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(charSequence);

        return (matcher.matches());
    }
}
