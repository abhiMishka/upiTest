Basic app to test upi payment and the response the payment apps return back.

1.Airtel Money
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/airtel_money.jpg" align="centre" height="540"></br>



2.BHIM 
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/bhim.jpg" align="centre" height="540"></br>


3.Google Pay
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/google_pay.jpg" align="centre" height="540"></br>


4. Paytm
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/paytm.png" align="centre" height="540"></br>


5. PhonePe
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/phonePe.jpg" align="centre" height="540"></br>


6.TruCaller
<br href="url"><img src="https://github.com/abhiMishka/upiTest/blob/master/response/trucaller.png" align="centre" height="540"></br>

Note : for the app to result correct data from PhonePe app use following uri method
Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", payeeAddress)
                .appendQueryParameter("pn", payeeName)
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .appendQueryParameter("tr", "261433")
                .build();

original repo : https://github.com/ShahMalavS/UPI-DeepLinked
