# This script is for local,
# In the properties file, I have used credentials for railway mysql connection
CREATE DATABASE carmanager;
CREATE user "carmanage" IDENTIFIED BY "Carinfomanage@2023";
GRANT ALL ON carmanager.* TO 'carmanage'@'your_local_host';