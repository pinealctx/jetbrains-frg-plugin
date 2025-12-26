#!/usr/bin/env bash
antlr4 -o ../gen -no-listener -visitor -package gen -Dlanguage=Go FrgParser.g4