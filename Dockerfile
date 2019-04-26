FROM payara/server-full

# Setup configuration
USER payara
COPY postgresql-42.2.5.jar /opt/payara5/glassfish/domains/domain1/lib
# Copy the exact domain that I used previously..
COPY /home/redmar/Documents/school/payara5/glassfish/domains/domain1/ /opt/payara41/glassfish/domains/
