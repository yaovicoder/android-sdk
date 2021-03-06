package com.nanjcoin.sdk.nanj

import android.content.Context
import android.text.TextUtils
import com.nanjcoin.sdk.model.TransactionResponse
import com.nanjcoin.sdk.model.TxRelayData
import com.nanjcoin.sdk.nanj.listener.*
import com.nanjcoin.sdk.ui.barcodereader.NANJQrCodeActivity
import com.nanjcoin.sdk.ui.nfc.NANJNfcActivity
import com.nanjcoin.sdk.util.*
import com.google.gson.Gson
import com.nanjcoin.sdk.smartcontract.MetaNANJCOINManager
import com.nanjcoin.sdk.smartcontract.NANJSmartContract
import com.nanjcoin.sdk.smartcontract.TxRelay
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.*
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Bytes32
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.*
import org.web3j.protocol.Web3j
import org.web3j.utils.Numeric
import java.math.BigDecimal
import java.math.BigInteger

/**
 * ____________________________________
 *
 * Generator: NANJ Team - support@nanjcoin.com
 * CreatedAt: 4/15/18
 * ____________________________________
 */

class NANJWallet {

    companion object {
        const val WALLET_ADDRESS = "WALLET_ADDRESS"
        const val QRCODE_REQUEST_CODE: Int = 10001
        const val QRCODE_RESULT_CODE: Int = 10003
        const val NFC_REQUEST_CODE: Int = 10002
        const val NFC_RESULT_CODE: Int = 10004
    }

    var nanjDatabase: com.nanjcoin.sdk.database.NANJDatabase? = null
    private var config: com.nanjcoin.sdk.model.NANJConfigModel? = null
    var nanjAddress: String? = ""
    var address: String = ""
    var name: String = "No name"
    var privateKey: String? = null
    var keystore: String? = null
    var credentials: Credentials? = null
    private lateinit var _web3j: Web3j
    var web3j: Web3j? = null
        set(value) {
            _web3j = value ?: return
        }
    private var nanjSmartContract: NANJSmartContract? = null
    private var txRelay: TxRelay? = null
    private var metaNANJCOINManager: MetaNANJCOINManager? = null

    fun init() {
        initNANJSmartContract()
        initMetaNANJCOINManager()
        initTxRelay()
    }

    private fun initNANJSmartContract() {
        this.nanjSmartContract = NANJSmartContract.load(
                NANJConfig.SMART_CONTRACT_ADDRESS,
                _web3j,
                credentials!!
        )
    }

    private fun initTxRelay() {
        this.txRelay = TxRelay.load(
                NANJConfig.TX_RELAY_ADDRESS,
                _web3j,
                credentials!!
        )
    }

    private fun initMetaNANJCOINManager() {
        this.metaNANJCOINManager = MetaNANJCOINManager.load(
                web3j = _web3j,
                credentials = credentials!!
        )
    }

    fun getAmountNanj(): BigInteger {
        val nanjAddress = getNANJWallet()
        if (nanjAddress == NANJConfig.UNKNOWN_NANJ_WALLET) return BigInteger.ZERO
        return nanjSmartContract?.balanceOf(nanjAddress)?.send()
                ?: BigInteger.ZERO
    }

    fun getTransactions(page: Int, offset: Int = 20, listener: NANJTransactionsListener) {
        doAsync(
                {
                    listener.onTransferFailure()
                },
                {
                    val nanjAddress = getNANJWallet()
                    val url = String.format(
                            NANJConfig.URL_TRANSACTION,
                            nanjAddress,
                            offset,
                            page
                    )
                    NetworkUtil.retrofit.create(Api::class.java)
                            .getNANJTransactions(url)
                            .subscribeOn(Schedulers.single())
                            .subscribe(
                                    {
                                        val transactionList: TransactionResponse? = Gson().fromJson(it.string(), TransactionResponse::class.java)
                                        listener.onTransferSuccess(transactionList?.data)
                                    },
                                    {
                                        it.printStackTrace()
                                        listener.onTransferFailure()
                                    }
                            )
                }
        )
    }

    fun getNANJWalletAsync(listener: GetNANJWalletListener) {
        doAsync(
                { listener.onError() },
                {
                    val address = getNANJWallet()
                    listener.onSuccess(address)
                }
        )
    }

    fun getNANJWallet(): String {
        if (!TextUtils.isEmpty(nanjAddress)) return nanjAddress!!
        val ad = metaNANJCOINManager!!.getWallet(address).send()
        if (NANJConfig.UNKNOWN_NANJ_WALLET != ad) {
            nanjAddress = ad
            nanjDatabase?.updateWallet(address, ad)
        }
        return ad
    }

    fun createNANJWallet(listener: CreateNANJWalletListener? = null) {
        doAsync(
                {
                    it.printStackTrace()
                    listener?.onError()
                },
                {
                    val param = arrayListOf<Type<*>>(Address(address))
                    val createNanjWalletFunction = Function("createWallet", param, arrayListOf())
                    sendFunctionToServer(
                            function = createNanjWalletFunction,
                            error = { listener?.onError() },
                            success = { listener?.onCreateProcess() }
                    )
                }
        )
    }

    fun sendNANJCoin(toAddress: String, amount: String, message: String = "test", listener: SendNANJCoinListener? = null) {
        doAsync(
                {
                    it.printStackTrace()
                    listener?.onError()
                }
        ) {
            if (config == null) {
                throw Exception("Please get config of nanj coin via NetworkUtil.getRetrofit().create(Api.class).getNANJCoinConfig(NANJConfig.NANJ_SERVER_CONFIG)")
            }
            val nanjAddress = metaNANJCOINManager!!.getWallet(address).send()
            val nanjAmount = BigDecimal(amount).multiply(BigDecimal.TEN.pow(8)).toBigInteger()
            val param = arrayListOf<Type<*>>(
                    Address(toAddress),
                    Uint256(nanjAmount),
                    DynamicBytes(message.toByteArray())
            )
            val f = Function("transfer", param, arrayListOf())
            val data = FunctionEncoder.encode(f)
            val hexData = Numeric.hexStringToByteArray(data)
            val appHash = config!!.data?.appHash
            println("appHash   : $appHash")
            val params = arrayListOf<Type<*>>(
                    Address(address),
                    Address(nanjAddress),
                    Address(NANJConfig.SMART_CONTRACT_ADDRESS),
                    Uint256.DEFAULT,
                    DynamicBytes(hexData),
                    Bytes32(Numeric.hexStringToByteArray(appHash))
            )
            val forwardToFunction = Function("forwardTo", params, arrayListOf())
            NetworkUtil.retrofit.create(Api::class.java).getNANJNonce("${NANJConfig.NANJCOIN_URL}/api/relayNonce?sender=$address&ts=${System.currentTimeMillis()}")
                    .subscribe(
                            {
                                if(it.statusCode == 200) {
                                    sendFunctionToServer(
                                            nonce = it.data,
                                            function = forwardToFunction,
                                            error = { listener?.onError() },
                                            success = { listener?.onSuccess() }
                                    )
                                } else {
                                    listener?.onError()
                                }
                            },
                            {
                                it.printStackTrace()
                                listener?.onError()
                            }
                    )
        }
    }

    private fun sendFunctionToServer(nonce : Int = 0, function: Function, error: () -> Unit, success: () -> Unit) {

        val encodeFunction = FunctionEncoder.encode(function)
        val pad = nonce.toString(16).padStart(64, '0')
        val hashInput = "0x1900" +
                NANJConfig.TX_RELAY_ADDRESS.cleanHexPrefix() +
                NANJConfig.WHITE_LIST_OWNER.cleanHexPrefix() +
                pad +
                NANJConfig.META_NANJCOIN_MANAGER.cleanHexPrefix() +
                encodeFunction.cleanHexPrefix()
        val sign = Sign.signMessage(Numeric.hexStringToByteArray(hashInput), credentials!!.ecKeyPair)
        val hash = Hash.sha3(hashInput)
        val restData = TxRelayData(
                r = Numeric.toHexString(sign.r),
                s = Numeric.toHexString(sign.s),
                v = sign.v.toInt(),
                data = encodeFunction,
                nonce = nonce.toString(),
                dest = NANJConfig.META_NANJCOIN_MANAGER,
                hash = hash
        )

        val requestBody = RequestBody.create(MediaType.parse("application/json"), restData.toString())

        NetworkUtil.retrofit.create(Api::class.java).postCreateNANJWallet(
                NANJConfig.NANJ_SERVER_ADDRESS + "?ts=${System.currentTimeMillis()}",
                requestBody
        )
                .subscribe(
                        {
                            if(it.statusCode == 200) {
                                success.invoke()
                            } else {
                                error.invoke()
                            }
                        },
                        {
                            it.printStackTrace()
                            error.invoke()
                        }
                )
    }


    fun sendNANJCoinByQrCode(context: Context) {
        context.launchActivity<NANJQrCodeActivity>(QRCODE_REQUEST_CODE)
    }

    fun sendNANJCoinByNfcCode(context: Context) {
        context.launchActivity<NANJNfcActivity>(NFC_REQUEST_CODE)
    }
}

inline fun String.cleanHexPrefix() = this.removePrefix("0x")
 