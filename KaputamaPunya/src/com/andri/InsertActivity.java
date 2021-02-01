package com.andri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {
private EditText kode_mk;
private EditText nama_mk;
private EditText txtsks;
private Button btnSimpan;

// Seusuaikan url dengan nama domain yang anda gunakan
private String url = "http://10.0.2.2/androidKaputama/addMataPel.php";
/**
* Method yang dipanggil pada saat aplikaasi dijalankan
* */

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.tambah);
kode_mk= (EditText) findViewById(R.id.txtkodeMK);
nama_mk = (EditText) findViewById(R.id.txtnamaMK);
txtsks = (EditText) findViewById(R.id.txtSks);
btnSimpan = (Button) findViewById(R.id.btnSimpan);
// daftarkan even onClick pada btnSimpan
btnSimpan.setOnClickListener(new Button.OnClickListener() {


public void onClick(View v) {
try {
// setiap parameter yang akan dikirim melalui http
// harus encode agar
// dapat terbaca dengan baik oleh server
String kodemk = URLEncoder.encode(kode_mk.getText()
.toString(), "utf-8");

String namamk = URLEncoder.encode(nama_mk.getText()
.toString(), "utf-8");

String sks = URLEncoder.encode(txtsks.getText()
		.toString(), "utf-8");

url += "?kode=" + kodemk + "&nama=" + namamk + "&sks=" + sks;
getRequest(url);

} catch (UnsupportedEncodingException e) {
// TODO Auto-generated catch block

e.printStackTrace();
}
}
});
}

/**
* Method untuk Mengirimkan data kes erver event by button login diklik
*
* @param view

*/
public void getRequest(String Url) {
Toast.makeText(this, "Tambah Data " + Url + " ", Toast.LENGTH_SHORT)
.show();

HttpClient client = new DefaultHttpClient();
HttpGet request = new HttpGet(url);

try {
HttpResponse response = client.execute(request);
Toast.makeText(this, "Tambah Data " + request(response) + " ",
Toast.LENGTH_SHORT).show();
} catch (Exception ex) {
Toast.makeText(this, "Tambah Data Gagal !", Toast.LENGTH_SHORT)
.show();
}
}

/**
* Method untuk Menenrima data dari server
*
* @param response
* @return
*/

public static String request(HttpResponse response) {
String result = "";
try {
InputStream in = response.getEntity().getContent();
BufferedReader reader = new BufferedReader(
new InputStreamReader(in));
StringBuilder str = new StringBuilder();
String line = null;
while ((line = reader.readLine()) != null) {
str.append(line + "\n");
}
in.close();
result = str.toString();
} catch (Exception ex) {
result = "Error";
}
return result;
}
}

