#!/bin/bash

function one_line_pem {
    echo "`awk 'NF {sub(/\\n/, ""); printf "%s\\\\\\\n",$0;}' $1`"
}

function json_ccp {
    local PP=$(one_line_pem $4)
    local CP=$(one_line_pem $5)
    sed -e "s/\${ORG}/$1/" \
        -e "s/\${P0PORT}/$2/" \
        -e "s/\${CAPORT}/$3/" \
        -e "s#\${PEERPEM}#$PP#" \
        -e "s#\${CAPEM}#$CP#" \
        organizations/ccp-template.json
}

function yaml_ccp {
    local PP=$(one_line_pem $4)
    local CP=$(one_line_pem $5)
    sed -e "s/\${ORG}/$1/" \
        -e "s/\${P0PORT}/$2/" \
        -e "s/\${CAPORT}/$3/" \
        -e "s#\${PEERPEM}#$PP#" \
        -e "s#\${CAPEM}#$CP#" \
        organizations/ccp-template.yaml | sed -e $'s/\\\\n/\\\n          /g'
}

ORG=1
P0PORT=7051
CAPORT=7054
PEERPEM=organizations/peerOrganizations/org1.tasksharingnet.com/tlsca/tlsca.org1.tasksharingnet.com-cert.pem
CAPEM=organizations/peerOrganizations/org1.tasksharingnet.com/ca/ca.org1.tasksharingnet.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org1.tasksharingnet.com/connection-org1.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org1.tasksharingnet.com/connection-org1.yaml

ORG=2
P0PORT=9051
CAPORT=8054
PEERPEM=organizations/peerOrganizations/org2.tasksharingnet.com/tlsca/tlsca.org2.tasksharingnet.com-cert.pem
CAPEM=organizations/peerOrganizations/org2.tasksharingnet.com/ca/ca.org2.tasksharingnet.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org2.tasksharingnet.com/connection-org2.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org2.tasksharingnet.com/connection-org2.yaml

ORG=3
P0PORT=10051
CAPORT=10054
PEERPEM=organizations/peerOrganizations/org3.tasksharingnet.com/tlsca/tlsca.org3.tasksharingnet.com-cert.pem
CAPEM=organizations/peerOrganizations/org3.tasksharingnet.com/ca/ca.org3.tasksharingnet.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org3.tasksharingnet.com/connection-org3.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org3.tasksharingnet.com/connection-org3.yaml

ORG=4
P0PORT=11051
CAPORT=11054
PEERPEM=organizations/peerOrganizations/org4.tasksharingnet.com/tlsca/tlsca.org4.tasksharingnet.com-cert.pem
CAPEM=organizations/peerOrganizations/org4.tasksharingnet.com/ca/ca.org4.tasksharingnet.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org4.tasksharingnet.com/connection-org4.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org4.tasksharingnet.com/connection-org4.yaml

ORG=5
P0PORT=12051
CAPORT=12054
PEERPEM=organizations/peerOrganizations/org5.tasksharingnet.com/tlsca/tlsca.org5.tasksharingnet.com-cert.pem
CAPEM=organizations/peerOrganizations/org5.tasksharingnet.com/ca/ca.org5.tasksharingnet.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org5.tasksharingnet.com/connection-org5.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > organizations/peerOrganizations/org5.tasksharingnet.com/connection-org5.yaml
