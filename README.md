# seeDNA [![Build Status](https://travis-ci.org/mcupak/seedna.svg?branch=master)](https://travis-ci.org/mcupak/seedna) [![GitHub license](https://img.shields.io/badge/license-apache2-blue.svg)](https://raw.githubusercontent.com/mcupak/seedna/master/LICENSE)

A command-line tool for generating images based from genetic data.

##  Development

To build:

```
./gradlew build
```

To run:

```
./gradlew bootrun
```

To run as an executable:

```
java -jar build/libs/seedna-0.0.1-SNAPSHOT-boot.jar
```

## Usage

Supported parameters:

- `-i` (`--inputPath`) - Location of the input file. Defaults to `input.png`.
- `-o` (`--outputPath`) - Location of the output file. Defaults to `output.png`.
- `-if` (`--inputFormat`) - Input format. Currently, the only supported value is `SNP` (23andme export file). Defaults to `SNP`.
- `-of` (`--outputFormat`) - Output format. Currently, the only supported value is `PNG`. Defaults to `PNG`.

Sample run:

```
java -jar build/libs/seedna-0.0.1-SNAPSHOT-boot.jar -i /tmp/23andme.txt -o /tmp/output.png
```