#!/bin/bash

# remove tasksharingnet mounted volumes
docker-compose -f compose/compose-test-net.yaml -f compose/docker/docker-compose-test-net.yaml down --volumes --remove-orphans

# remove org3 crypto material
sudo rm -rf organizations/fabric-ca/org3/msp
rm -f organizations/fabric-ca/org3/ca-cert.pem
rm -f organizations/fabric-ca/org3/tls-cert.pem
rm -f organizations/fabric-ca/org3/fabric-ca-server.db
rm -f organizations/fabric-ca/org3/IssuerPublicKey
rm -f organizations/fabric-ca/org3/IssuerRevocationPublicKey

# remove org4 crypto material
sudo rm -rf organizations/fabric-ca/org4/msp
rm -f organizations/fabric-ca/org4/ca-cert.pem
rm -f organizations/fabric-ca/org4/tls-cert.pem
rm -f organizations/fabric-ca/org4/fabric-ca-server.db
rm -f organizations/fabric-ca/org4/IssuerPublicKey
rm -f organizations/fabric-ca/org4/IssuerRevocationPublicKey

# remove org5 crypto material
sudo rm -rf organizations/fabric-ca/org5/msp
rm -f organizations/fabric-ca/org5/ca-cert.pem
rm -f organizations/fabric-ca/org5/tls-cert.pem
rm -f organizations/fabric-ca/org5/fabric-ca-server.db
rm -f organizations/fabric-ca/org5/IssuerPublicKey
rm -f organizations/fabric-ca/org5/IssuerRevocationPublicKey
