package com.nanjsdk.sample;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.nanjcoin.sdk.nanj.listener.NANJCreateWalletListener;
import com.nanjcoin.sdk.nanj.listener.NANJImportWalletListener;
import com.nanjcoin.sdk.nanj.NANJWalletManager;

/**
 * ____________________________________
 *
 * Generator: NANJ Team - support@nanjcoin.com
 * CreatedAt: 4/25/18
 * ____________________________________
 */
public class WalletsActivity extends AppCompatActivity {

	private LoadingDialog _progressDialog;
	private NANJWalletManager nanjWalletManager;
	private WalletsFragment _walletsFragment;
	private String _password;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallets);
		_progressDialog = new LoadingDialog(this);
		_password = getIntent().getStringExtra(Const.BUNDLE_KEY_PASSWORD);
		nanjWalletManager = NANJWalletManager.instance;
		_walletsFragment = (WalletsFragment) getSupportFragmentManager().findFragmentById(R.id.walletsFragment);
		_walletsFragment.setNanjWalletManager(nanjWalletManager);
		setupActionBar();
		findViewById(R.id.btnCreateWallet).setOnClickListener(view -> createWallet());
		findViewById(R.id.btnImportWallet).setOnClickListener(view -> importWalletDialog());
	}

	private void setupActionBar() {
//		setSupportActionBar(findViewById(R.id.toolbar));
		setTitle("Wallet list");
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_back_24px);
			actionBar.setHomeActionContentDescription("Back");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void createWallet() {
		_progressDialog.show();
		nanjWalletManager.createWallet(
			new NANJCreateWalletListener() {
				@Override
				public void onCreatedWalletSuccess(@Nullable String backup) {
					runOnUiThread(() -> {
						_progressDialog.dismiss();
						backupWallet(backup);
						_walletsFragment.setData(nanjWalletManager.getWalletList());
					});
				}
				@Override
				public void onWalletCreationError() {
					runOnUiThread(() -> {
						_progressDialog.dismiss();
						Toast.makeText(WalletsActivity.this, "Create wallet failure.", Toast.LENGTH_LONG).show();
					});
				}
			}
		);
	}

	private void backupWallet(String wallet) {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, wallet);
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, "Backup wallet"));
	}

	@SuppressLint("InflateParams")
	private void importWalletDialog() {
		View view = LayoutInflater.from(this).inflate(R.layout.dialog_import_json, null);
		RadioButton radioButton = view.findViewById(R.id.rdJson);
		AppCompatEditText edText = view.findViewById(R.id.edPrivateKey);
		new AlertDialog.Builder(this)
			.setTitle("Import wallet")
			.setView(view)
			.setNegativeButton("Cancel", null)
			.setPositiveButton("Import", (dialogInterface, i) -> {
				String s = edText.getText().toString();
				_progressDialog.show();
				if (radioButton.isChecked()) {
					nanjWalletManager.importWallet(_password, s, nanjImportWalletListener);
				} else {
					nanjWalletManager.importWallet(s, nanjImportWalletListener);
				}
			})
			.show();
	}

	private NANJImportWalletListener nanjImportWalletListener = new NANJImportWalletListener() {
		@Override
		public void onImportWalletSuccess() {
			runOnUiThread(() -> {
				_progressDialog.dismiss();
				_walletsFragment.setData(nanjWalletManager.getWalletList());
			});
		}

		@Override
		public void onImportWalletError() {
			runOnUiThread(() -> {
				_progressDialog.dismiss();
				Toast.makeText(WalletsActivity.this, "Import wallet failure.", Toast.LENGTH_LONG).show();
			});
		}
	};
}
