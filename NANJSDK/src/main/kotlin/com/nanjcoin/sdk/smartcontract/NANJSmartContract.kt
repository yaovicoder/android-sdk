package com.nanjcoin.sdk.smartcontract

import org.web3j.abi.EventEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Bool
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.Event
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.Utf8String
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.abi.datatypes.generated.Uint8
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.RemoteCall
import org.web3j.protocol.core.methods.request.EthFilter
import org.web3j.protocol.core.methods.response.Log
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.tx.Contract
import org.web3j.tx.TransactionManager
import rx.Observable
import java.math.BigInteger
import java.util.*

/**
 *
 * Auto generated code.
 *
 * **Do not modify!**
 *
 * Please use the [web3j command line tools](https://docs.web3j.io/command_line.html),
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * [codegen module](https://github.com/web3j/web3j/tree/master/codegen) to update.
 *
 *
 * Generated with web3j version 3.3.1.
 */
class NANJSmartContract : Contract {

    protected constructor(contractAddress: String, web3j: Web3j, credentials: Credentials, gasPrice: BigInteger, gasLimit: BigInteger) : super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit) {}

    protected constructor(contractAddress: String, web3j: Web3j, transactionManager: TransactionManager, gasPrice: BigInteger, gasLimit: BigInteger) : super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit) {}

    fun getFrozenFundsEvents(transactionReceipt: TransactionReceipt): List<FrozenFundsEventResponse> {
        val event = Event("FrozenFunds",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Bool>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<FrozenFundsEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = FrozenFundsEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.target = eventValues.indexedValues[0].value as String
            typedResponse.frozen = eventValues.nonIndexedValues[0].value as Boolean
            responses.add(typedResponse)
        }
        return responses
    }

    fun frozenFundsEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<FrozenFundsEventResponse> {
        val event = Event("FrozenFunds",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Bool>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = FrozenFundsEventResponse()
            typedResponse.log = log
            typedResponse.target = eventValues.indexedValues[0].value as String
            typedResponse.frozen = eventValues.nonIndexedValues[0].value as Boolean
            typedResponse
        }
    }

    fun getLockedFundsEvents(transactionReceipt: TransactionReceipt): List<LockedFundsEventResponse> {
        val event = Event("LockedFunds",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<LockedFundsEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = LockedFundsEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.target = eventValues.indexedValues[0].value as String
            typedResponse.locked = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun lockedFundsEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<LockedFundsEventResponse> {
        val event = Event("LockedFunds",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = LockedFundsEventResponse()
            typedResponse.log = log
            typedResponse.target = eventValues.indexedValues[0].value as String
            typedResponse.locked = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun getBurnEvents(transactionReceipt: TransactionReceipt): List<BurnEventResponse> {
        val event = Event("Burn",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<BurnEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = BurnEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.amount = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun burnEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<BurnEventResponse> {
        val event = Event("Burn",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = BurnEventResponse()
            typedResponse.log = log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.amount = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun getMintEvents(transactionReceipt: TransactionReceipt): List<MintEventResponse> {
        val event = Event("Mint",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<MintEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = MintEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.to = eventValues.indexedValues[0].value as String
            typedResponse.amount = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun mintEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<MintEventResponse> {
        val event = Event("Mint",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = MintEventResponse()
            typedResponse.log = log
            typedResponse.to = eventValues.indexedValues[0].value as String
            typedResponse.amount = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun getMintFinishedEvents(transactionReceipt: TransactionReceipt): List<MintFinishedEventResponse> {
        val event = Event("MintFinished",
                Arrays.asList(),
                Arrays.asList())
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<MintFinishedEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = MintFinishedEventResponse()
            typedResponse.log = eventValues.log
            responses.add(typedResponse)
        }
        return responses
    }

    fun mintFinishedEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<MintFinishedEventResponse> {
        val event = Event("MintFinished",
                Arrays.asList(),
                Arrays.asList())
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = MintFinishedEventResponse()
            typedResponse.log = log
            typedResponse
        }
    }

    fun getOwnershipTransferredEvents(transactionReceipt: TransactionReceipt): List<OwnershipTransferredEventResponse> {
        val event = Event("OwnershipTransferred",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList())
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<OwnershipTransferredEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = OwnershipTransferredEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.previousOwner = eventValues.indexedValues[0].value as String
            typedResponse.newOwner = eventValues.indexedValues[1].value as String
            responses.add(typedResponse)
        }
        return responses
    }

    fun ownershipTransferredEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<OwnershipTransferredEventResponse> {
        val event = Event("OwnershipTransferred",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList())
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = OwnershipTransferredEventResponse()
            typedResponse.log = log
            typedResponse.previousOwner = eventValues.indexedValues[0].value as String
            typedResponse.newOwner = eventValues.indexedValues[1].value as String
            typedResponse
        }
    }

    fun getTransferExEvents(transactionReceipt: TransactionReceipt): List<ExTransferEventResponse> {
        val event = Event("Transfer",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }, object : TypeReference<DynamicBytes>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<ExTransferEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = ExTransferEventResponse()
            typedResponse.log = eventValues.log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.data = eventValues.indexedValues[2].value as ByteArray
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun transferExEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<ExTransferEventResponse> {
        val event = Event("Transfer",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }, object : TypeReference<DynamicBytes>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = ExTransferEventResponse()
            typedResponse.log = log
            typedResponse.from = eventValues.indexedValues[0].value as String
            typedResponse.to = eventValues.indexedValues[1].value as String
            typedResponse.data = eventValues.indexedValues[2].value as ByteArray
            typedResponse.value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun getTransferEvents(transactionReceipt: TransactionReceipt): List<TransferEventResponse> {
        val event = Event("Transfer",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<TransferEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = TransferEventResponse()
            typedResponse.log = eventValues.log
            typedResponse._from = eventValues.indexedValues[0].value as String
            typedResponse._to = eventValues.indexedValues[1].value as String
            typedResponse._value = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun transferEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<TransferEventResponse> {
        val event = Event("Transfer",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = TransferEventResponse()
            typedResponse.log = log
            typedResponse._from = eventValues.indexedValues[0].value as String
            typedResponse._to = eventValues.indexedValues[1].value as String
            typedResponse._value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun getApprovalEvents(transactionReceipt: TransactionReceipt): List<ApprovalEventResponse> {
        val event = Event("Approval",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val valueList = extractEventParametersWithLog(event, transactionReceipt)
        val responses = ArrayList<ApprovalEventResponse>(valueList.size)
        for (eventValues in valueList) {
            val typedResponse = ApprovalEventResponse()
            typedResponse.log = eventValues.log
            typedResponse._owner = eventValues.indexedValues[0].value as String
            typedResponse._spender = eventValues.indexedValues[1].value as String
            typedResponse._value = eventValues.nonIndexedValues[0].value as BigInteger
            responses.add(typedResponse)
        }
        return responses
    }

    fun approvalEventObservable(startBlock: DefaultBlockParameter, endBlock: DefaultBlockParameter): Observable<ApprovalEventResponse> {
        val event = Event("Approval",
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }, object : TypeReference<Address>() {

                }),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        val filter = EthFilter(startBlock, endBlock, getContractAddress())
        filter.addSingleTopic(EventEncoder.encode(event))
        return web3j.ethLogObservable(filter).map { log ->
            val eventValues = extractEventParametersWithLog(event, log)
            val typedResponse = ApprovalEventResponse()
            typedResponse.log = log
            typedResponse._owner = eventValues.indexedValues[0].value as String
            typedResponse._spender = eventValues.indexedValues[1].value as String
            typedResponse._value = eventValues.nonIndexedValues[0].value as BigInteger
            typedResponse
        }
    }

    fun mintingFinished(): RemoteCall<Boolean> {
        val function = Function("mintingFinished",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Bool>() {

                }))
        return executeRemoteCallSingleValueReturn(function, Boolean::class.java)
    }

    fun name(): RemoteCall<String> {
        val function = Function("name",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun approve(_spender: String, _value: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "approve",
                Arrays.asList(org.web3j.abi.datatypes.Address(_spender),
                        org.web3j.abi.datatypes.generated.Uint256(_value)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun totalSupply(): RemoteCall<BigInteger> {
        val function = Function("totalSupply",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun transferFrom(_from: String, _to: String, _value: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "transferFrom",
                Arrays.asList(org.web3j.abi.datatypes.Address(_from),
                        org.web3j.abi.datatypes.Address(_to),
                        org.web3j.abi.datatypes.generated.Uint256(_value)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun decimals(): RemoteCall<BigInteger> {
        val function = Function("decimals",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint8>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun mint(_to: String, _unitAmount: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "mint",
                Arrays.asList(org.web3j.abi.datatypes.Address(_to),
                        org.web3j.abi.datatypes.generated.Uint256(_unitAmount)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun founder(): RemoteCall<String> {
        val function = Function("founder",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun distributeAmount(): RemoteCall<BigInteger> {
        val function = Function("distributeAmount",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun AAcontributors(): RemoteCall<String> {
        val function = Function("AAcontributors",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun lockupAccounts(targets: List<String>, unixTimes: List<BigInteger>): RemoteCall<TransactionReceipt> {
        val function = Function(
                "lockupAccounts",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.DynamicArray(
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address::class.java)),
                        org.web3j.abi.datatypes.DynamicArray(
                                org.web3j.abi.Utils.typeMap(unixTimes, org.web3j.abi.datatypes.generated.Uint256::class.java))),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun balanceOf(_owner: String): RemoteCall<BigInteger> {
        val function = Function("balanceOf",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(_owner)),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun getNanjWallet(_owner: String): RemoteCall<BigInteger> {
        val function = Function("getWallet",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(_owner)),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun finishMinting(): RemoteCall<TransactionReceipt> {
        val function = Function(
                "finishMinting",
                Arrays.asList(),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun activityFunds(): RemoteCall<String> {
        val function = Function("activityFunds",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun owner(): RemoteCall<String> {
        val function = Function("owner",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun preSeasonGame(): RemoteCall<String> {
        val function = Function("preSeasonGame",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun distributeAirdrop(addresses: List<String>, amount: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "distributeAirdrop",
                Arrays.asList(org.web3j.abi.datatypes.DynamicArray(
                        org.web3j.abi.Utils.typeMap(addresses, org.web3j.abi.datatypes.Address::class.java)),
                        org.web3j.abi.datatypes.generated.Uint256(amount)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun symbol(): RemoteCall<String> {
        val function = Function("symbol",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Utf8String>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun burn(_from: String, _unitAmount: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "burn",
                Arrays.asList(org.web3j.abi.datatypes.Address(_from),
                        org.web3j.abi.datatypes.generated.Uint256(_unitAmount)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun lockedFundsForthefuture(): RemoteCall<String> {
        val function = Function("lockedFundsForthefuture",
                Arrays.asList(),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Address>() {

                }))
        return executeRemoteCallSingleValueReturn(function, String::class.java)
    }

    fun autoDistribute(weiValue: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "autoDistribute",
                Arrays.asList(),
                emptyList())
        return executeRemoteCallTransaction(function, weiValue)
    }

    fun transfer(_to: String, _value: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "transfer",
                Arrays.asList(org.web3j.abi.datatypes.Address(_to),
                        org.web3j.abi.datatypes.generated.Uint256(_value)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun frozenAccount(param0: String): RemoteCall<Boolean> {
        val function = Function("frozenAccount",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(param0)),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Bool>() {

                }))
        return executeRemoteCallSingleValueReturn(function, Boolean::class.java)
    }

    fun transfer(_to: String, _value: BigInteger, _data: ByteArray): RemoteCall<TransactionReceipt> {
        val function = Function(
                "transfer",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(_to),
                        org.web3j.abi.datatypes.generated.Uint256(_value),
                        org.web3j.abi.datatypes.DynamicBytes(_data)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun freezeAccounts(targets: List<String>, isFrozen: Boolean?): RemoteCall<TransactionReceipt> {
        val function = Function(
                "freezeAccounts",
                Arrays.asList(org.web3j.abi.datatypes.DynamicArray(
                        org.web3j.abi.Utils.typeMap(targets, org.web3j.abi.datatypes.Address::class.java)),
                        org.web3j.abi.datatypes.Bool(isFrozen!!)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun unlockUnixTime(param0: String): RemoteCall<BigInteger> {
        val function = Function("unlockUnixTime",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(param0)),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun setDistributeAmount(_unitAmount: BigInteger): RemoteCall<TransactionReceipt> {
        val function = Function(
                "setDistributeAmount",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.generated.Uint256(_unitAmount)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun allowance(_owner: String, _spender: String): RemoteCall<BigInteger> {
        val function = Function("allowance",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(_owner),
                        org.web3j.abi.datatypes.Address(_spender)),
                Arrays.asList<TypeReference<*>>(object : TypeReference<Uint256>() {

                }))
        return executeRemoteCallSingleValueReturn(function, BigInteger::class.java)
    }

    fun distributeAirdrop(addresses: List<String>, amounts: List<BigInteger>): RemoteCall<TransactionReceipt> {
        val function = Function(
                "distributeAirdrop",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.DynamicArray(
                        org.web3j.abi.Utils.typeMap(addresses, org.web3j.abi.datatypes.Address::class.java)),
                        org.web3j.abi.datatypes.DynamicArray(
                                org.web3j.abi.Utils.typeMap(amounts, org.web3j.abi.datatypes.generated.Uint256::class.java))),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun collectTokens(addresses: List<String>, amounts: List<BigInteger>): RemoteCall<TransactionReceipt> {
        val function = Function(
                "collectTokens",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.DynamicArray(
                        org.web3j.abi.Utils.typeMap(addresses, org.web3j.abi.datatypes.Address::class.java)),
                        org.web3j.abi.datatypes.DynamicArray(
                                org.web3j.abi.Utils.typeMap(amounts, org.web3j.abi.datatypes.generated.Uint256::class.java))),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun transferOwnership(newOwner: String): RemoteCall<TransactionReceipt> {
        val function = Function(
                "transferOwnership",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(newOwner)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    fun transfer(_to: String, _value: BigInteger, _data: ByteArray, _custom_fallback: String): RemoteCall<TransactionReceipt> {
        val function = Function(
                "transfer",
                Arrays.asList<Type<*>>(org.web3j.abi.datatypes.Address(_to),
                        org.web3j.abi.datatypes.generated.Uint256(_value),
                        org.web3j.abi.datatypes.DynamicBytes(_data),
                        org.web3j.abi.datatypes.Utf8String(_custom_fallback)),
                emptyList())
        return executeRemoteCallTransaction(function)
    }

    class FrozenFundsEventResponse {
        var log: Log? = null

        var target: String? = null

        var frozen: Boolean? = null
    }

    class LockedFundsEventResponse {
        var log: Log? = null

        var target: String? = null

        var locked: BigInteger? = null
    }

    class BurnEventResponse {
        var log: Log? = null

        var from: String? = null

        var amount: BigInteger? = null
    }

    class MintEventResponse {
        var log: Log? = null

        var to: String? = null

        var amount: BigInteger? = null
    }

    class MintFinishedEventResponse {
        var log: Log? = null
    }

    class OwnershipTransferredEventResponse {
        var log: Log? = null

        var previousOwner: String? = null

        var newOwner: String? = null
    }

    class ExTransferEventResponse {
        var log: Log? = null

        var from: String? = null

        var to: String? = null

        var data: ByteArray? = null

        var value: BigInteger? = null
    }

    class TransferEventResponse {
        var log: Log? = null

        var _from: String? = null

        var _to: String? = null

        var _value: BigInteger? = null
    }

    class ApprovalEventResponse {
        var log: Log? = null

        var _owner: String? = null

        var _spender: String? = null

        var _value: BigInteger? = null
    }

    companion object {
        private val GAS_PRICE = BigInteger.valueOf(20_000_000L)
        private val GAS_LIMIT = BigInteger.valueOf(1_000_000L)

        private const val BINARY = "0x60606040526040805190810160405280600881526020017f4e414e4a434f494e000000000000000000000000000000000000000000000000815250600290805190602001906200005192919062000605565b506040805190810160405280600481526020017f4e414e4a00000000000000000000000000000000000000000000000000000000815250600390805190602001906200009f92919062000605565b506008600460006101000a81548160ff021916908360ff1602179055506729a2241af62c000060055560006006556000600760006101000a81548160ff021916908315150217905550731b746e35c90050e3cc236479051467f623ca14f7600760016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073aec7cf1da46a76ad3a41580e28e778ff8849ec49600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073728899556c836ce7f8aa73e8bace3241f17077bf600960006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073b80c43bf83f7cb6c44b84b436b01ea92da5dabff600a60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034156200024857600080fd5b33600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620003356064620003186019600554620005aa6401000000000262003bdd179091906401000000009004565b620005e964010000000002620042df179091906401000000009004565b600b6000600760019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550620003e36064620003c66037600554620005aa6401000000000262003bdd179091906401000000009004565b620005e964010000000002620042df179091906401000000009004565b600b6000600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555062000491606462000474600a600554620005aa6401000000000262003bdd179091906401000000009004565b620005e964010000000002620042df179091906401000000009004565b600b6000600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506200053f606462000522600a600554620005aa6401000000000262003bdd179091906401000000009004565b620005e964010000000002620042df179091906401000000009004565b600b6000600a60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550620006b4565b6000806000841415620005c15760009150620005e2565b8284029050828482811515620005d357fe5b04141515620005de57fe5b8091505b5092915050565b6000808284811515620005f857fe5b0490508091505092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200064857805160ff191683800117855562000679565b8280016001018555821562000679579182015b82811115620006785782518255916020019190600101906200065b565b5b5090506200068891906200068c565b5090565b620006b191905b80821115620006ad57600081600090555060010162000693565b5090565b90565b61432680620006c46000396000f300606060405260043610610196576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806305d2035b146101a057806306fdde03146101cd578063095ea7b31461025b57806318160ddd146102b557806323b872dd146102de578063313ce5671461035757806340c10f19146103865780634d853ee5146103e05780634f25eced146104355780635ab892481461045e57806364ddc605146104ec57806370a08231146105865780637d64bcb4146105d357806385a156af146106005780638da5cb5b14610655578063911a9ac0146106aa57806394594625146106ff57806395d89b411461077a5780639dc29fac14610808578063a245b7741461084a578063a8f11eb91461089f578063a9059cbb146108a9578063b414d4b614610903578063be45fd6214610954578063c341b9f6146109f1578063cbbe974b14610a56578063d39b1d4814610aa3578063dd62ed3e14610ac6578063dd92459414610b32578063f0dc417114610be4578063f2fde38b14610c96578063f6368f8a14610ccf575b61019e610daf565b005b34156101ab57600080fd5b6101b361113e565b604051808215151515815260200191505060405180910390f35b34156101d857600080fd5b6101e0611151565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610220578082015181840152602081019050610205565b50505050905090810190601f16801561024d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561026657600080fd5b61029b600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506111f9565b604051808215151515815260200191505060405180910390f35b34156102c057600080fd5b6102c86112eb565b6040518082815260200191505060405180910390f35b34156102e957600080fd5b61033d600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506112f5565b604051808215151515815260200191505060405180910390f35b341561036257600080fd5b61036a611806565b604051808260ff1660ff16815260200191505060405180910390f35b341561039157600080fd5b6103c6600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001909190505061181d565b604051808215151515815260200191505060405180910390f35b34156103eb57600080fd5b6103f3611a14565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561044057600080fd5b610448611a3a565b6040518082815260200191505060405180910390f35b341561046957600080fd5b610471611a40565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156104b1578082015181840152602081019050610496565b50505050905090810190601f1680156104de5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104f757600080fd5b61058460048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050611a79565b005b341561059157600080fd5b6105bd600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611c7d565b6040518082815260200191505060405180910390f35b34156105de57600080fd5b6105e6611cc6565b604051808215151515815260200191505060405180910390f35b341561060b57600080fd5b610613611d8e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561066057600080fd5b610668611db4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156106b557600080fd5b6106bd611dda565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561070a57600080fd5b610760600480803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019091905050611e00565b604051808215151515815260200191505060405180910390f35b341561078557600080fd5b61078d61224f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156107cd5780820151818401526020810190506107b2565b50505050905090810190601f1680156107fa5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561081357600080fd5b610848600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506122f7565b005b341561085557600080fd5b61085d6124af565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6108a7610daf565b005b34156108b457600080fd5b6108e9600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506124d5565b604051808215151515815260200191505060405180910390f35b341561090e57600080fd5b61093a600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061266f565b604051808215151515815260200191505060405180910390f35b341561095f57600080fd5b6109d7600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061268f565b604051808215151515815260200191505060405180910390f35b34156109fc57600080fd5b610a546004808035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919080351515906020019091905050612820565b005b3415610a6157600080fd5b610a8d600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506129c2565b6040518082815260200191505060405180910390f35b3415610aae57600080fd5b610ac460048080359060200190919050506129da565b005b3415610ad157600080fd5b610b1c600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050612a40565b6040518082815260200191505060405180910390f35b3415610b3d57600080fd5b610bca60048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050612ac7565b604051808215151515815260200191505060405180910390f35b3415610bef57600080fd5b610c7c60048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050612fce565b604051808215151515815260200191505060405180910390f35b3415610ca157600080fd5b610ccd600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061349f565b005b3415610cda57600080fd5b610d95600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506135f7565b604051808215151515815260200191505060405180910390f35b6000600654118015610e245750600654600b6000600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410155b8015610e80575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b8015610eca5750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b1515610ed557600080fd5b6000341115610f4157600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f193505050501515610f4057600080fd5b5b610fb7600654600b6000600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b6000600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611070600654600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff16600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef6006546040518082815260200191505060405180910390a3565b600760009054906101000a900460ff1681565b6111596142b7565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111ef5780601f106111c4576101008083540402835291602001916111ef565b820191906000526020600020905b8154815290600101906020018083116111d257829003601f168201915b5050505050905090565b600081600c60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040518082815260200191505060405180910390a36001905092915050565b6000600554905090565b60008073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16141580156113335750600082115b801561137e575081600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410155b8015611406575081600c60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410155b8015611462575060001515600d60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156114be575060001515600d60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156115085750600e60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b80156115525750600e60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b151561155d57600080fd5b6115af82600b60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061164482600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061171682600c60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600c60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a3600190509392505050565b6000600460009054906101000a900460ff16905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561187b57600080fd5b600760009054906101000a900460ff1615151561189757600080fd5b6000821115156118a657600080fd5b6118bb82600554613bbf90919063ffffffff16565b60058190555061191382600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff167f0f6798a560793a54c3bcfe86a93cde1e73087d944c0ea20544137d4121396885836040518082815260200191505060405180910390a28273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b600760019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60065481565b6040805190810160405280601781526020017f7379626974202620e382afe383aae38397e3819fe3829300000000000000000081525081565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611ad757600080fd5b60008351118015611ae9575081518351145b1515611af457600080fd5b600090505b8251811015611c78578181815181101515611b1057fe5b90602001906020020151600e60008584815181101515611b2c57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101515611b7d57600080fd5b8181815181101515611b8b57fe5b90602001906020020151600e60008584815181101515611ba757fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508281815181101515611bfd57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff167f1bd6fb9fa2c39ce5d0d2afa1eaba998963eb5f553fd862c94f131aa9e35c15778383815181101515611c4c57fe5b906020019060200201516040518082815260200191505060405180910390a28080600101915050611af9565b505050565b6000600b60008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611d2457600080fd5b600760009054906101000a900460ff16151515611d4057600080fd5b6001600760006101000a81548160ff0219169083151502179055507fae5184fba832cb2b1f702aca6117b8d265eaf03ad33eb133f19dde0f5920fa0860405160405180910390a16001905090565b600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060008084118015611e15575060008551115b8015611e71575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b8015611ebb5750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b1515611ec657600080fd5b611edd6305f5e10085613bdd90919063ffffffff16565b9350611ef3855185613bdd90919063ffffffff16565b915081600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410151515611f4357600080fd5b600090505b84518110156121ae5760008582815181101515611f6157fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614158015611ff6575060001515600d60008784815181101515611fa057fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156120575750600e6000868381518110151561200f57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b151561206257600080fd5b6120cb84600b6000888581518110151561207857fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b600087848151811015156120dd57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550848181518110151561213357fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040518082815260200191505060405180910390a38080600101915050611f48565b61220082600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060019250505092915050565b6122576142b7565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156122ed5780601f106122c2576101008083540402835291602001916122ed565b820191906000526020600020905b8154815290600101906020018083116122d057829003601f168201915b5050505050905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561235357600080fd5b6000811180156123a2575080600b60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410155b15156123ad57600080fd5b6123ff81600b60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061245781600554613ba690919063ffffffff16565b6005819055508173ffffffffffffffffffffffffffffffffffffffff167fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca5826040518082815260200191505060405180910390a25050565b600a60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006124df6142cb565b60008311801561253f575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b801561259b575060001515600d60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156125e55750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b801561262f5750600e60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b151561263a57600080fd5b61264384613c18565b1561265a57612653848483613c2b565b9150612668565b612665848483614005565b91505b5092915050565b600d6020528060005260406000206000915054906101000a900460ff1681565b600080831180156126f0575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b801561274c575060001515600d60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156127965750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b80156127e05750600e60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b15156127eb57600080fd5b6127f484613c18565b1561280b57612804848484613c2b565b9050612819565b612816848484614005565b90505b9392505050565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561287e57600080fd5b6000835111151561288e57600080fd5b600090505b82518110156129bd57600083828151811015156128ac57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff16141515156128d957600080fd5b81600d600085848151811015156128ec57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550828181518110151561295557fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff167f48335238b4855f35377ed80f164e8c6f3c366e54ac00b96a6402d4a9814a03a583604051808215151515815260200191505060405180910390a28080600101915050612893565b505050565b600e6020528060005260406000206000915090505481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515612a3657600080fd5b8060068190555050565b6000600c60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6000806000808551118015612add575083518551145b8015612b39575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b8015612b835750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b1515612b8e57600080fd5b60009150600090505b8451811015612d575760008482815181101515612bb057fe5b90602001906020020151118015612bf5575060008582815181101515612bd257fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614155b8015612c68575060001515600d60008784815181101515612c1257fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b8015612cc95750600e60008683815181101515612c8157fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b1515612cd457600080fd5b612d026305f5e1008583815181101515612cea57fe5b90602001906020020151613bdd90919063ffffffff16565b8482815181101515612d1057fe5b9060200190602002018181525050612d488482815181101515612d2f57fe5b9060200190602002015183613bbf90919063ffffffff16565b91508080600101915050612b97565b81600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410151515612da557600080fd5b600090505b8451811015612f2d57612e338482815181101515612dc457fe5b90602001906020020151600b60008885815181101515612de057fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008784815181101515612e4557fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508481815181101515612e9b57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8684815181101515612f0157fe5b906020019060200201516040518082815260200191505060405180910390a38080600101915050612daa565b612f7f82600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060019250505092915050565b6000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561302f57600080fd5b60008551118015613041575083518551145b151561304c57600080fd5b60009150600090505b84518110156133fe576000848281518110151561306e57fe5b906020019060200201511180156130b357506000858281518110151561309057fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614155b8015613126575060001515600d600087848151811015156130d057fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156131875750600e6000868381518110151561313f57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b151561319257600080fd5b6131c06305f5e10085838151811015156131a857fe5b90602001906020020151613bdd90919063ffffffff16565b84828151811015156131ce57fe5b906020019060200201818152505083818151811015156131ea57fe5b90602001906020020151600b6000878481518110151561320657fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015151561325857600080fd5b6132d8848281518110151561326957fe5b90602001906020020151600b6000888581518110151561328557fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b600087848151811015156132ea57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061335c848281518110151561334357fe5b9060200190602002015183613bbf90919063ffffffff16565b91503373ffffffffffffffffffffffffffffffffffffffff16858281518110151561338357fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef86848151811015156133d257fe5b906020019060200201516040518082815260200191505060405180910390a38080600101915050613055565b61345082600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060019250505092915050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156134fb57600080fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151561353757600080fd5b8073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008084118015613658575060001515600d60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156136b4575060001515600d60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515145b80156136fe5750600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b80156137485750600e60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205442115b151561375357600080fd5b61375c85613c18565b15613b905783600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101515156137af57600080fd5b61380184600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061389684600b60008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508473ffffffffffffffffffffffffffffffffffffffff166000836040518082805190602001908083835b6020831015156139285780518252602082019150602081019050602083039250613903565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207c01000000000000000000000000000000000000000000000000000000009004903387876040518563ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828051906020019080838360005b83811015613a095780820151818401526020810190506139ee565b50505050905090810190601f168015613a365780820380516001836020036101000a031916815260200191505b50935050505060006040518083038185886187965a03f193505050501515613a5a57fe5b826040518082805190602001908083835b602083101515613a905780518252602082019150602081019050602083039250613a6b565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208573ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c16876040518082815260200191505060405180910390a48473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040518082815260200191505060405180910390a360019050613b9e565b613b9b858585614005565b90505b949350505050565b6000828211151515613bb457fe5b818303905092915050565b6000808284019050838110151515613bd357fe5b8091505092915050565b6000806000841415613bf25760009150613c11565b8284029050828482811515613c0357fe5b04141515613c0d57fe5b8091505b5092915050565b600080823b905060008111915050919050565b60008083600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410151515613c7c57600080fd5b613cce84600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550613d6384600b60008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508490508073ffffffffffffffffffffffffffffffffffffffff1663c0ee0b8a3386866040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015613e6b578082015181840152602081019050613e50565b50505050905090810190601f168015613e985780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b1515613eb857600080fd5b6102c65a03f11515613ec957600080fd5b505050826040518082805190602001908083835b602083101515613f025780518252602082019150602081019050602083039250613edd565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208573ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c16876040518082815260200191505060405180910390a48473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040518082815260200191505060405180910390a360019150509392505050565b600082600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015151561405557600080fd5b6140a783600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613ba690919063ffffffff16565b600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061413c83600b60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054613bbf90919063ffffffff16565b600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550816040518082805190602001908083835b6020831015156141b55780518252602082019150602081019050602083039250614190565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c16866040518082815260200191505060405180910390a48373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef856040518082815260200191505060405180910390a3600190509392505050565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b60008082848115156142ed57fe5b04905080915050929150505600a165627a7a72305820a330e62d2e9c8454f3dfb8b31aae4a36af214e8dd36cbe619a33844ac63b831f0029"

        fun deploy(web3j: Web3j, credentials: Credentials, gasPrice: BigInteger = GAS_PRICE, gasLimit: BigInteger = GAS_LIMIT): RemoteCall<NANJSmartContract> {
            return Contract.deployRemoteCall(NANJSmartContract::class.java, web3j, credentials, gasPrice, gasLimit, BINARY, "")
        }

        fun deploy(web3j: Web3j, transactionManager: TransactionManager, gasPrice: BigInteger = GAS_PRICE, gasLimit: BigInteger = GAS_LIMIT): RemoteCall<NANJSmartContract> {
            return Contract.deployRemoteCall(NANJSmartContract::class.java, web3j, transactionManager, gasPrice, gasLimit, BINARY, "")
        }

        fun load(contractAddress: String, web3j: Web3j, credentials: Credentials, gasPrice: BigInteger = GAS_PRICE, gasLimit: BigInteger = GAS_LIMIT): NANJSmartContract {
            return NANJSmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit)
        }

        fun load(contractAddress: String, web3j: Web3j, transactionManager: TransactionManager, gasPrice: BigInteger = GAS_PRICE, gasLimit: BigInteger = GAS_LIMIT): NANJSmartContract {
            return NANJSmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit)
        }
    }
}
