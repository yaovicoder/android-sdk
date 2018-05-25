package com.bc.core.nanj

import android.app.Activity
import android.support.v4.app.Fragment
import com.bc.core.ui.barcodereader.NANJQrCodeActivity
import com.bc.core.ui.nfc.NANJNfcActivity
import com.bc.core.util.launchActivity
import com.bc.core.util.uiThread
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.web3j.crypto.*
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Numeric
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutionException
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 4/15/18
 * ____________________________________
 */

class NANJWallet {

    companion object {
        private val ZERO_AMOUNT_NANJ_COIN = BigInteger.valueOf(0)
        private val REAL_AMOUNT_NANJ_COIN = BigInteger.valueOf(1_000_000_000)
        private val REAL_AMOUNT_ETH_COIN = BigInteger.valueOf(1_000_000_000_000_000_000)

        const val WALLET_ADDRESS = "WALLET_ADDRESS"
        const val QRCODE_REQUEST_CODE: Int = 10001
        const val QRCODE_RESULT_CODE: Int = 10003
        const val NFC_REQUEST_CODE: Int = 10002
        const val NFC_RESULT_CODE: Int = 10004
    }

    var address: String = ""
    var name: String = "No name"
    var privatekey: String? = null
    var cridentals: Credentials? = null
    private lateinit var _web3j: Web3j
    var web3j: Web3j? = null
        set(value) {
            _web3j = value ?: return
        }
    var contract: NANJSmartContract? = null

    fun getAmountEth(): BigInteger {
        return try {
            _web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
                    .send()
                    .balance
        } catch (e: Exception) {
            e.printStackTrace()
            ZERO_AMOUNT_NANJ_COIN
        }
    }

    fun getNANJCoinObservable() = contract?.balanceOf(address)?.observable()

    fun getAmountNanj(): BigInteger {
        return try {
            contract?.balanceOf(address)?.send()
                    ?: ZERO_AMOUNT_NANJ_COIN
        } catch (e: Exception) {
            e.printStackTrace()
            ZERO_AMOUNT_NANJ_COIN
        }
    }

    fun getTransactions(page: Int, offset: Int = 20, listener: NANJTransactionsListener) {
        doAsync(
                {
                    it.printStackTrace()
                    uiThread { listener.onTransferFailure() }
                },
                {
                    val url = URL(
                            String.format(
                                    NANJConfig.URL_TRANSACTION,
                                    contract?.contractAddress,
                                    address,
                                    page,
                                    offset
                            )
                    )
                    println("nanj transactions :  $url")
                    val httpURLConnection = url.openConnection() as HttpURLConnection
                    val stringBuilder = StringBuilder()
                    val transactionList: TransactionResponse?
                    try {
                        val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
                        var line: String? = bufferedReader.readLine()
                        while (line != null) {
                            stringBuilder.append(line).append("\n")
                            line = bufferedReader.readLine()
                        }
                        bufferedReader.close()
                        val toString = stringBuilder.toString()
                        println(toString)
                        transactionList = Gson().fromJson(toString, TransactionResponse::class.java)
                    } finally {
                        httpURLConnection.disconnect()
                    }

                    testSign()

                    uiThread { listener.onTransferSuccess(transactionList?.transactions) }
                }
        )
    }

    private fun testSign() {
        val msg = "Hello World".toByteArray()
        /* val sign = Sign.signMessage(msg.toByteArray(), cridentals!!.ecKeyPair)
         val hash = Hash.sha3(msg.toByteArray())
         val pub = Sign.signedMessageToKey(msg.toByteArray(), sign)
         println("hash = ${Numeric.toHexString(hash)}")
         println("r = ${Numeric.toHexString(sign.r)}")
         println("s = ${Numeric.toHexString(sign.s)}")
         println("v = ${sign.v}")
         println("key = ${pub.toString()}")
         println("aaa = ${cridentals!!.ecKeyPair.publicKey.toString()}")*/

       val p = "d8816e6d65b327575cdfe58dbe3ed83ade7079dc4885ef51cf38e795a6d71020"
        val pub = Sign.publicKeyFromPrivate(BigInteger(p, 16))
        println("rrr ->  $pub")
        val hash = msg
        val s = Numeric.hexStringToByteArray("0x6612d114ac4769ad9968eb014a6029da381328106e17e48f58c3a256542ee85e")
        val r = Numeric.hexStringToByteArray("0x26cdc16571a273bcba4a3160bfc499e791757ea3e79c3caef29465f2e886291c")
        val v = 27.toByte()
        val sd = Sign.SignatureData(v, r, s)
        val dd = Sign.signedMessageToKey(hash, sd)
        val re = Sign.signedMessageToKey(Hash.sha3(hash), sd)
        println("wtf ${dd}")
        println("wtf ${dd.toString(16)}")

        /*var ms = "Hello World".toByteArray()
        val sd = Sign.signMessage(ms, cridentals!!.ecKeyPair)
        val key = Sign.signedMessageToKey(ms, sd)

        println("key = $key")
        println("key = ${cridentals!!.ecKeyPair.publicKey}")*/
    }

    fun sentNANJCoin(toAddress: String, amount: String, transferListener: NANJTransactionListener) {
        doAsync(
                {
                    println("my wallet transfer error")
                    it.printStackTrace()
                    uiThread { transferListener.onTransferFailure() }
                },
                {
                    contract?.transfer(toAddress, BigInteger(amount))?.send()
                    uiThread { transferListener.onTransferSuccess() }
                }
        )
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    private fun getNonce(address: String): BigInteger {
        web3j?.let {
            val ethGetTransactionCount = it.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).sendAsync().get()
            return ethGetTransactionCount.transactionCount
        }
        return ZERO_AMOUNT_NANJ_COIN
    }

    fun sendNANJCoinByQrCode(activity: Activity) {
        activity.launchActivity<NANJQrCodeActivity>(QRCODE_REQUEST_CODE)
    }

    fun sendNANJCoinByQrCode(fragment: Fragment) {
        fragment.launchActivity<NANJQrCodeActivity>(QRCODE_REQUEST_CODE)
    }

    fun sendNANJCoinByNfcCode(activity: Activity) {
        activity.launchActivity<NANJNfcActivity>(NFC_REQUEST_CODE)
    }

    fun sendNANJCoinByNfcCode(fragment: Fragment) {
        fragment.launchActivity<NANJNfcActivity>(NFC_REQUEST_CODE)
    }
}
 