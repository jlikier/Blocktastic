#!/bin/bash
java -cp "bin:lib/lwjgl-2.5/jar/*:lib/slick/*" -Djava.library.path="lib/lwjgl-2.5/native/macosx" blocktastic.client.Client
