#!/bin/bash
#
# Copyright IBM Corp All Rights Reserved
#
# SPDX-License-Identifier: Apache-2.0
#

# This script brings up a Hyperledger Fabric network for testing smart contracts
# and applications. The test network consists of two organizations with one
# peer each, and a single node Raft ordering service. Users can also use this
# script to create a channel deploy a chaincode on the channel
#
# prepending $PWD/../bin to PATH to ensure we are picking up the correct binaries
# this may be commented out to resolve installed version of tools if desired
#
# However using PWD in the path has the side effect that location that
# this script is run from is critical. To ease this, get the directory
# this script is actually in and infer location from there. (putting first)


########
# ORG1 #
########

export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/peers/peer0.org1.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/users/Admin@org1.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:7051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# package chaincode from org1 (do this only once for your network)
peer lifecycle chaincode package example.tar.gz --path ../../../../eclipse-workspace/Example/lib/build/install/lib --lang java --label exampleV0
peer lifecycle chaincode calculatepackageid example.tar.gz

# install chaincode on peer0 org1 and approve for org1
peer lifecycle chaincode install example.tar.gz --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name example --version 0.1 --init-required --package-id exampleV0:b5bb0794ba4f7db2a4455ee344747cb63d84eddbdcf822ef995993ac8834ca42 --sequence 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name example --version 0.1 --sequence 1 --output json --init-required

########
# ORG2 #
########

export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/peers/peer0.org2.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/users/Admin@org2.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:9051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install chaincode on peer0 org2 and approve for org2
peer lifecycle chaincode install example.tar.gz --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name example --version 0.1 --init-required --package-id exampleV0:b5bb0794ba4f7db2a4455ee344747cb63d84eddbdcf822ef995993ac8834ca42 --sequence 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name example --version 0.1 --sequence 1 --output json --init-required

########
# ORG3 #
########

export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org3MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/peers/peer0.org3.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/users/Admin@org3.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:10051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install chaincode on peer0 org3 and approve for org3
peer lifecycle chaincode install example.tar.gz --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name example --version 0.1 --init-required --package-id exampleV0:b5bb0794ba4f7db2a4455ee344747cb63d84eddbdcf822ef995993ac8834ca42 --sequence 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name example --version 0.1 --sequence 1 --output json --init-required

########
# ORG4 #
########

export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org4MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/peers/peer0.org4.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/users/Admin@org4.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:11051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install chaincode on peer0 org4 and approve for org4
peer lifecycle chaincode install example.tar.gz --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name example --version 0.1 --init-required --package-id exampleV0:b5bb0794ba4f7db2a4455ee344747cb63d84eddbdcf822ef995993ac8834ca42 --sequence 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name example --version 0.1 --sequence 1 --output json --init-required

########
# ORG5 #
########

export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org5MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/peers/peer0.org5.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/users/Admin@org5.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:12051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install chaincode on peer0 org5 and approve for org5
peer lifecycle chaincode install example.tar.gz --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name example --version 0.1 --init-required --package-id exampleV0:b5bb0794ba4f7db2a4455ee344747cb63d84eddbdcf822ef995993ac8834ca42 --sequence 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name example --version 0.1 --sequence 1 --output json --init-required


export CORE_PEER_TLS_ROOTCERT_FILE_ORG1=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/peers/peer0.org1.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG2=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/peers/peer0.org2.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG3=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/peers/peer0.org3.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG4=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/peers/peer0.org4.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG5=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/peers/peer0.org5.tasksharingnet.com/tls/ca.crt

# commit chaincode to globalchannel from org5 (do this only once for the channel)
peer lifecycle chaincode commit -o localhost:7050 --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA --channelID globalchannel --name example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 --version 0.1 --sequence 1 --init-required
peer lifecycle chaincode querycommitted -o localhost:7050 --channelID globalchannel

# Initialize chaincode
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 --isInit -c '{"Args":[]}'

# Transaction: instantiate
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 -c '{"Args":["instantiate"]}'

# Transaction: issue
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 -c '{"Args":["issue","Angelo Vera","001","12 May 2022, 10am","May 12 2023","1000000"]}'

# Transaction: buy
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 -c '{"Args":["buy","Angelo Vera","001","Angelo Vera","Ekram Hossain","5000","May 13 2022"]}'

# Transaction: redeem
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n example --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 -c '{"Args":["redeem","Angelo Vera","001","Ekram Hossain","May 13 2023"]}'








