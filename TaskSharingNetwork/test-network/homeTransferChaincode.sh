#!/bin/bash

BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[0;33m'
GREEN='\033[0;32m'
NC='\033[0m'

########
# ORG1 #
########

printf "${BLUE}Packckaging homeTransfer chaincode:${NC}\n"
sleep 1
# Org1 env var
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/peers/peer0.org1.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/users/Admin@org1.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:7051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# package
peer lifecycle chaincode package homeTransfer.tar.gz --path ../../../../eclipse-workspace/HomeTransfer/lib/build/install/lib --lang java --label homeTransferV0
cc_pkg=$(peer lifecycle chaincode calculatepackageid homeTransfer.tar.gz)
printf "${GREEN}Chaincode was packaged${NC}\n\n"
sleep 1

# install
printf "${BLUE}Installing chaincode in Org1:${NC}\n"
peer lifecycle chaincode install homeTransfer.tar.gz --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
printf "${GREEN}Chaincode Installed in Org1${NC}\n\n"
sleep 1

# approve
printf "${BLUE}Approving chaincode for Org1:${NC}\n"
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --version 0.1 --init-required --package-id $cc_pkg --sequence 1
printf "${GREEN}Chaincode approved for Org1${NC}\n\n"
sleep 1

printf "${BLUE}Checking chaincode approvals:${NC}\n"
sleep 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name homeTransfer --version 0.1 --sequence 1 --output json --init-required

########
# ORG2 #
########

# Org2 env var
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/peers/peer0.org2.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/users/Admin@org2.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:9051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install
printf "${BLUE}Installing Chaincode in Org2:${NC}\n"
peer lifecycle chaincode install homeTransfer.tar.gz --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
printf "${GREEN}Chaincode Installed in Org2${NC}\n\n"
sleep 1

# approve
printf "${BLUE}Approving chaincode for Org2:${NC}\n"
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --version 0.1 --init-required --package-id $cc_pkg --sequence 1
printf "${GREEN}Chaincode approved for Org2${NC}\n\n"
sleep 1

# check
printf "${BLUE}Checking chaincode approvals:${NC}\n"
sleep 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name homeTransfer --version 0.1 --sequence 1 --output json --init-required

########
# ORG3 #
########

# Org3 env var
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org3MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/peers/peer0.org3.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/users/Admin@org3.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:10051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install
printf "${BLUE}Installing chaincode in Org3:${NC}\n"
peer lifecycle chaincode install homeTransfer.tar.gz --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
printf "${GREEN}Chaincode Installed in Org3${NC}\n\n"
sleep 1

# approve
printf "${BLUE}Approving chaincode for Org3:${NC}\n"
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --version 0.1 --init-required --package-id $cc_pkg --sequence 1
printf "${GREEN}Chaincode approved for Org3${NC}\n\n"
sleep 1

# check
printf "${BLUE}Checking approvals:${NC}\n"
sleep 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name homeTransfer --version 0.1 --sequence 1 --output json --init-required

########
# ORG4 #
########

# Org4 env var
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org4MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/peers/peer0.org4.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/users/Admin@org4.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:11051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install
printf "${BLUE}Installing Chaincode in Org4:${NC}\n"
peer lifecycle chaincode install homeTransfer.tar.gz --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
printf "${GREEN}Chaincode installed in Org4${NC}\n\n"
sleep 1

# approve
printf "${BLUE}Approving Chaincode for Org4:${NC}\n"
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --version 0.1 --init-required --package-id $cc_pkg --sequence 1
printf "${GREEN}Chaincode approved for Org4${NC}\n\n"
sleep 1

# check
printf "${BLUE}Checking approvals:${NC}\n"
sleep 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name homeTransfer --version 0.1 --sequence 1 --output json --init-required

########
# ORG5 #
########

# Org5 env var
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org5MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/peers/peer0.org5.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/users/Admin@org5.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:12051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem

# install
printf "${BLUE}Installing Chaincode in Org5:${NC}\n"
peer lifecycle chaincode install homeTransfer.tar.gz --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
peer lifecycle chaincode queryinstalled --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE
printf "${GREEN}Chaincode installed in Org5${NC}\n\n"
sleep 1

# approve
printf "${BLUE}Approving Chaincode for Org5:${NC}\n"
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --version 0.1 --init-required --package-id $cc_pkg --sequence 1
printf "${GREEN}Chaincode approved for Org5${NC}\n\n"
sleep 1

# check
printf "${BLUE}Checking approvals:${NC}\n"
sleep 1
peer lifecycle chaincode checkcommitreadiness --channelID globalchannel --name homeTransfer --version 0.1 --sequence 1 --output json --init-required

export CORE_PEER_TLS_ROOTCERT_FILE_ORG1=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/peers/peer0.org1.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG2=${PWD}/organizations/peerOrganizations/org2.tasksharingnet.com/peers/peer0.org2.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG3=${PWD}/organizations/peerOrganizations/org3.tasksharingnet.com/peers/peer0.org3.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG4=${PWD}/organizations/peerOrganizations/org4.tasksharingnet.com/peers/peer0.org4.tasksharingnet.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE_ORG5=${PWD}/organizations/peerOrganizations/org5.tasksharingnet.com/peers/peer0.org5.tasksharingnet.com/tls/ca.crt

# committ
printf "${BLUE}Committing homeTransfer Chaincode to globalchannel:${NC}\n"
peer lifecycle chaincode commit -o localhost:7050 --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA --channelID globalchannel --name homeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 --version 0.1 --sequence 1 --init-required
printf "${GREEN}Chaincode committed:${NC}\n"
sleep 1

# check
printf "${GREEN}Query committments:${NC}\n"
sleep 1
peer lifecycle chaincode querycommitted -o localhost:7050 --channelID globalchannel
sleep 1

# initialize
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/peers/peer0.org1.tasksharingnet.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.tasksharingnet.com/users/Admin@org1.tasksharingnet.com/msp
export CORE_PEER_ADDRESS=localhost:7051
export ORDERER_CA=${PWD}/organizations/ordererOrganizations/tasksharingnet.com/orderers/orderer.tasksharingnet.com/msp/tlscacerts/tlsca.tasksharingnet.com-cert.pem
printf "${BLUE}Initializing the Chaincode:${NC}\n"
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n homeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 --isInit -c '{"Args":[]}'
printf "${GREEN}Chaincode initialized:${NC}\n"
sleep 1

#peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.tasksharingnet.com --tls $CORE_PEER_TLS_ENABLED --cafile $ORDERER_CA -C globalchannel -n homeTransfer --peerAddresses localhost:7051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG1 --peerAddresses localhost:9051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG2 --peerAddresses localhost:10051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG3 --peerAddresses localhost:11051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG4 --peerAddresses localhost:12051 --tlsRootCertFiles $CORE_PEER_TLS_ROOTCERT_FILE_ORG5 -c '{"Args":["serviceRequest","001","localhost:7051","thisResources","atThisTime"]}'



