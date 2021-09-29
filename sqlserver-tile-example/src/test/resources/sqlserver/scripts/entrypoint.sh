#!/bin/sh

# Start SQL Server in background
/opt/mssql/bin/sqlservr &

# Sleep 25 seconds ~ until Database is UP
sleep 25

# Execute initial setup
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P V3rY.S3CreT2020 -d master -i /usr/src/script/setup.sql && echo "Database and Schemas created"

# Wait for all background processess to finish. This won't happen, so our container would be running.
wait