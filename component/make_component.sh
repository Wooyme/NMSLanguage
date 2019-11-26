#!/usr/bin/env bash

COMPONENT_DIR="component_temp_dir"
LANGUAGE_PATH="$COMPONENT_DIR/jre/languages/nmsl"
if [[ -f ../native/slnative ]]; then
    INCLUDE_SLNATIVE="TRUE"
fi

rm -rf COMPONENT_DIR

mkdir -p "$LANGUAGE_PATH"
cp ../language/target/simplelanguage.jar "$LANGUAGE_PATH"

mkdir -p "$LANGUAGE_PATH/launcher"
cp ../launcher/target/sl-launcher.jar "$LANGUAGE_PATH/launcher/"
mkdir -p "$LANGUAGE_PATH/libs"
cp ../language/builtin/lib.nmsl "$LANGUAGE_PATH/libs/"
mkdir -p "$LANGUAGE_PATH/bin"
cp ../sl $LANGUAGE_PATH/bin/nmsl
if [[ $INCLUDE_SLNATIVE = "TRUE" ]]; then
    cp ../native/slnative $LANGUAGE_PATH/bin/nmslnative
fi

touch "$LANGUAGE_PATH/native-image.properties"

mkdir -p "$COMPONENT_DIR/META-INF"
{
    echo "Bundle-Name: NMSL";
    echo "Bundle-Symbolic-Name: nmsl";
    echo "Bundle-Version: 19.2.0";
    echo 'Bundle-RequireCapability: org.graalvm; filter:="(&(graalvm_version=19.2.0)(os_arch=amd64))"';
    echo "x-GraalVM-Polyglot-Part: True"
} > "$COMPONENT_DIR/META-INF/MANIFEST.MF"

(
cd $COMPONENT_DIR || exit 1
jar cfm ../nmsl-component.jar META-INF/MANIFEST.MF .

echo "bin/nmsl = ../jre/languages/nmsl/bin/nmsl" > META-INF/symlinks
if [[ $INCLUDE_SLNATIVE = "TRUE" ]]; then
    echo "bin/nmslnative = ../jre/languages/nmsl/bin/nmslnative" >> META-INF/symlinks
fi
jar uf ../nmsl-component.jar META-INF/symlinks

{
    echo "jre/languages/nmsl/bin/nmsl = rwxrwxr-x"
    echo "jre/languages/nmsl/bin/nmslnative = rwxrwxr-x"
} > META-INF/permissions
jar uf ../nmsl-component.jar META-INF/permissions
)
rm -rf $COMPONENT_DIR
