FROM jboss/wildfly
COPY ./target/bank-account-event-sourcing.war /opt/jboss/wildfly/standalone/deployments/