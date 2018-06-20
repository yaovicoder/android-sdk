package com.bc.core.nanj

import android.content.Context
import com.bc.core.database.NANJDatabase
import com.bc.core.model.NANJRateData
import com.bc.core.model.YenRate
import com.bc.core.nanj.listener.*
import com.bc.core.util.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.web3j.crypto.*
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * ____________________________________
 *
 * Generator: Hieu.TV - tvhieuit@gmail.com
 * CreatedAt: 4/15/18
 * ____________________________________
 */

open class NANJWalletManager {

    companion object {
        @JvmStatic fun Builder(): NANJWalletManager {
            return NANJWalletManager()
        }
    }

    private var wallets: MutableMap<String, NANJWallet> = mutableMapOf()
    private var _nanjDatabase: NANJDatabase? = null
    private val _web3j = Web3jFactory.build(HttpService(NANJConfig.URL_SERVER))
    var wallet: NANJWallet? = null
    private var metaNANJCOINManager: MetaNANJCOINManager? = MetaNANJCOINManager.load(
            web3j = _web3j,
            credentials = Credentials.create(Keys.createEcKeyPair()))

    fun setContext(context: Context): NANJWalletManager {
        _nanjDatabase = NANJDatabase(context)
        return this
    }

    fun build(): NANJWalletManager? {
        wallets = _nanjDatabase?.loadWallets() ?: return this
        if (wallets.isNotEmpty()) {
            enableWallet(0)
        }
        return this
    }

    fun getNANJWalletAsync(owner: String, listener: GetNANJWalletListener) {
        doAsync(
                { listener.onError()  },
                {
                    val address = metaNANJCOINManager!!.getWallet(owner).send()
                    if(NANJConfig.UNKNOWN_NANJ_WALLET != address) {
                        _nanjDatabase?.updateWallet(owner, address)
                    }
                    listener.onSuccess(address)
                }
        )
    }

    fun getNANJWallet(owner: String) = metaNANJCOINManager!!.getWallet(owner).send()

    fun addWallet(wallet: NANJWallet) {
        wallets[wallet.address] = wallet
    }

    fun getWalletList(): MutableList<NANJWallet> = wallets.values.toMutableList()

    /**
     * Import a wallet from keystore
     *
     * @param password
     * @param fileKeystore
     *
     * */
    fun importWallet(password: String, fileKeystore: File, nanjWalletListener: NANJImportWalletListener) {
        doAsync(
                {  nanjWalletListener.onImportWalletFailure() } ,
                {
                    val credentials = WalletUtils.loadCredentials(password, fileKeystore)
                    metaNANJCOINManager = MetaNANJCOINManager.load(
                            web3j = _web3j,
                            credentials = credentials
                    )
                    importWalletFromCredentials(credentials, "")
                    val nanjAddress = metaNANJCOINManager!!.getWallet(credentials.address).send()
                    println("wtf  $nanjAddress")
                    if (NANJConfig.UNKNOWN_NANJ_WALLET != nanjAddress) {
                        importWalletFromCredentials(credentials, nanjAddress)
                        nanjWalletListener.onImportWalletSuccess()
                    } else {

                        this@NANJWalletManager.wallet?.createNANJWallet(object : CreateNANJWalletListener {
                            override fun onError() {
                                nanjWalletListener.onImportWalletFailure()
                            }

                            override fun onCreateProcess() {
                                nanjWalletListener.onImportWalletSuccess()
                            }
                        })

                    }
                }
        )
    }

    /**
     * Import a wallet from json
     *
     * @param password
     * @param jsonKeystore
     *
     * */
    fun importWallet(password: String, jsonKeystore: String, nanjWalletListener: NANJImportWalletListener) {
        doAsync(
                { nanjWalletListener.onImportWalletFailure() },
                {
                    val objectMapper = ObjectMapper()
                    val walletFile = objectMapper.readValue(jsonKeystore, WalletFile::class.java)
                    val credentials = Credentials.create(Wallet.decrypt(password, walletFile))
                    metaNANJCOINManager = MetaNANJCOINManager.load(
                            web3j = _web3j,
                            credentials = credentials
                    )
                    importWalletFromCredentials(credentials, "")
                    val nanjAddress = metaNANJCOINManager!!.getWallet(credentials.address).send()
                    println("wtf  $nanjAddress")
                    if (NANJConfig.UNKNOWN_NANJ_WALLET != nanjAddress) {
                        importWalletFromCredentials(credentials, nanjAddress)
                        nanjWalletListener.onImportWalletSuccess()
                    } else {

                        this@NANJWalletManager.wallet?.createNANJWallet(object : CreateNANJWalletListener {
                            override fun onError() {
                                nanjWalletListener.onImportWalletFailure()
                            }

                            override fun onCreateProcess() {
                                nanjWalletListener.onImportWalletSuccess()
                            }
                        })

                    }
                }
        )
    }

    /**
     * Import a wallet from private key
     *
     * @param privateKey
     *
     * */
    fun importWallet(privateKey: String, nanjWalletListener: NANJImportWalletListener) {
        doAsync(
                { nanjWalletListener.onImportWalletFailure() },
                {
                    val credentials = Credentials.create(privateKey)
                    metaNANJCOINManager = MetaNANJCOINManager.load(
                            web3j = _web3j,
                            credentials = credentials
                    )
                    importWalletFromCredentials(credentials, "")
                    val nanjAddress = metaNANJCOINManager!!.getWallet(credentials.address).send()
                    println("wtf  $nanjAddress")
                    if (NANJConfig.UNKNOWN_NANJ_WALLET != nanjAddress) {
                        importWalletFromCredentials(credentials, nanjAddress)
                        nanjWalletListener.onImportWalletSuccess()
                    } else {

                        this@NANJWalletManager.wallet?.createNANJWallet(object : CreateNANJWalletListener {
                            override fun onError() {
                                nanjWalletListener.onImportWalletFailure()
                            }

                            override fun onCreateProcess() {
                                nanjWalletListener.onImportWalletSuccess()
                            }
                        })

                    }
                }
        )

    }

    private fun importWalletFromCredentials(credentials: Credentials, nanjAddress: String? = ""): NANJWallet {
        val nanjWallet = NANJWallet().apply {
            this.address = credentials.address
            this.cridentals = credentials
            this.privatekey = credentials.ecKeyPair.privateKey.toString(16)
            this.nanjAddress = nanjAddress
        }
        wallets[nanjWallet.address] = nanjWallet
        _nanjDatabase?.saveWallet(nanjWallet)
        enableWallet(nanjWallet)
        return nanjWallet
    }

    fun removeWallet(position: Int) {
        if(position >= wallets.keys.toMutableList().size) return
        val key = wallets.keys.toMutableList()[position]
        wallets.remove(key)
        this.wallet?.let {
            if (it.address == key) {
                this.wallet = null
            }
        }
    }

    fun removeWallet(wallet: NANJWallet) {
        _nanjDatabase?.removeWallet(wallet)
        wallets.remove(wallet.address)
        this.wallet?.let {
            if (it.address == wallet.address) {
                this.wallet = null
            }
        }
    }

    /**
     * Create a wallet.
     *
     * @param password
     *
     * */
    fun createWallet(password: String, createWalletListener: NANJCreateWalletListener) {
        doAsync(
                { createWalletListener.onCreateWalletFailure() } ,
                {
                    val ecKeyPair = Keys.createEcKeyPair()
                    val addressWallet = Wallet.createLight(password, ecKeyPair)
                    val credentials = Credentials.create(Wallet.decrypt(password, addressWallet))
                    val objectMapper = ObjectMapper()
                    val strWallet = objectMapper.writeValueAsString(addressWallet)
                    metaNANJCOINManager = MetaNANJCOINManager.load(
                            web3j = _web3j,
                            credentials = credentials
                    )
                    importWalletFromCredentials(credentials, "")

                        this@NANJWalletManager.wallet?.createNANJWallet(object : CreateNANJWalletListener {
                            override fun onError() {
                                createWalletListener.onCreateWalletFailure()
                            }

                            override fun onCreateProcess() {
                                    val wallet = importWalletFromCredentials(credentials, "")
                                    createWalletListener.onCreateProcess(strWallet, wallet)
                            }
                        })


                }
        )
    }

    fun enableWallet(wallet: NANJWallet) {
        val c = Credentials.create(wallet.privatekey)
        this.wallet = wallet.apply {
            this.web3j = _web3j
            this.cridentals = c
            nanjDatabase = _nanjDatabase
            init()
        }
    }

    fun enableWallet(position: Int) {
        if (position < 0 || position >= wallets.size) return // return if out size of list
        val list = wallets.keys.toMutableList()
        val key = list[position]
        val wallet = wallets[key]!!
        if (!wallet.nanjAddress.isNullOrBlank()) {
            this.enableWallet(wallet)
        }
    }

    fun getNANJRate(listener: NANJRateListener) {
        doAsync(
                {
                    it.printStackTrace()
                    listener.onFailure("error")
                },
                {
                    var nanjRate: BigDecimal = 0.toBigDecimal()
                    var yenRate: BigDecimal = 0.toBigDecimal()
                    NetworkUtil.GET(NANJConfig.URL_NANJ_RATE) {
                        val nanjRateData = Gson().fromJson(it, NANJRateData::class.java)
                        val quotes = nanjRateData.data.quotes
                        nanjRate = quotes.USD.price.divide(quotes.NANJ.price, 10, RoundingMode.HALF_UP)
                    }

                    NetworkUtil.GET(NANJConfig.URL_YEN_RATE) {
                        val yenData = Gson().fromJson(it, YenRate::class.java)
                        yenRate = yenData.Usd2Yen.value
                    }

                    listener.onSuccess(nanjRate.multiply(yenRate))
                }
        )
    }

}