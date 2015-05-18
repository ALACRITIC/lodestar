#/bin/bash
if [[ -z "$MESHRDF_HOME" ]]; then 
    echo "You must first define $MESHRDF_HOME\n" 1>&2
    exit 1
fi
if [[ ! -d $MESHRDF_HOME/opt/maven/current ]]; then
    echo "Maven is not installed in $MESHRDF_HOME\n" 1>&2
fi
which mvn >& /dev/null
if [[ $? -ne 0 ]]; then 
    . $MESHRDF_HOME/set_env_mesh
fi
(cd lode-core-api; mvn install)
(cd lode-core-servlet; mvn install)
(cd lode-virtuoso-impl; mvn install)
(cd web-ui; mvn package)
