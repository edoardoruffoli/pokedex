# Pokedex
[![codecov](https://codecov.io/gh/edoardoruffoli/pokedex/branch/main/graph/badge.svg)](https://codecov.io/gh/edoardoruffoli/pokedex)

## Overview

The Pokedex API provides information about Pokemon, including basic descriptions and translated descriptions based on certain criteria. 
This API leverages the PokéAPI and FunTranslations API to deliver the required data.

## API Requirements

### Endpoint 1 - Basic Pokemon Information

Fetches standard Pokemon information including its name, description, and habitat.

- **URL:** `/pokemon/<pokemon name>`
- **Method:** GET
- **Example Response:**
  ```json
  {
    "name": "mewtwo",
    "description": "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
    "habitat": "rare",
    "isLegendary": true
  }
  ```

### Endpoint 2 - Translated Pokemon Description

Fetches translated Pokemon description based on specific rules:
1. If the Pokemon’s habitat is "cave" or it is a legendary Pokemon, the Yoda translation is applied.
2. For all other Pokemon, the Shakespeare translation is applied.
3. If translation fails, the standard description is used.

- **URL:** `/pokemon/translated/<pokemon name>`
- **Method:** GET
- **Example Response:**
  ```json
  {
    "name": "mewtwo",
    "description": "Created by a scientist after years of horrific gene splicing and DNA engineering experiments, it was.",
    "habitat": "rare",
    "isLegendary": false
  }
  ```

## Installation

### Prerequisites

- Git (https://www.git-scm.com/downloads)
- Docker (https://docs.docker.com/get-docker/)

### Steps

1. Clone the repository:
   ```shell
   git clone https://github.com/edoardoruffoli/pokedex
   cd pokedex
   ```
2. Build the Docker image:
   ```shell
   docker build --tag pokedex .
   ```
3. Run the Docker container:
   ```shell
   docker run -d -p 8080:8080 --name pokedex-active pokedex
   ```

### Note
On Windows, it may be necessary to run the following script before cloning the repository:
```shell
git config --global core.autocrlf input
```
Related [issue](https://stackoverflow.com/a/71827011).

## Improvements for Production

### Caching
Since the number of Pokémon doesn't change frequently, we can persist the full list of Pokémon and their translations inside a
database or a file. 
If we prefer not to persist all possible calls, we can use in-memory caching solutions like Redis to store frequently requested data,
such as translation requests and the most commonly requested Pokémon. This will help efficiently serve the most common API calls.

### Rate Limiting
FunTranslations imposes a strict limit of 5 requests per hour, which is inadequate for a production environment. 
To resolve this issue, we should consider upgrading to a paid plan that removes the 5 requests per hour restriction, 
ensuring sufficient capacity to meet production demands. Implementing a persistence layer to store responses from previous 
requests will reduce the need for frequent external API calls by retrieving data directly from stored information whenever possible.

To monitor the frequency of calls to external APIs and implement cache scaling mechanisms, we could introduce a 
CloudWatch metric tied to the external API calls. 
In the event of frequent cache misses, scaling out the cache can be achieved through a scaling policy based on this metric.

### Logging and Deployment
Integrate a logging framework like Logback to ensure consistent log formatting. This will facilitate log aggregation 
and monitoring with tools such as ELK Stack (Elasticsearch, Logstash, Kibana).
Additionally, while we already have GitHub Actions for computing coverage, we can enhance our deployment process by linking these actions to Kubernetes, 
implementing CI/CD for more efficient and automated workflows.

### Formal API Documentation
Provide comprehensive API documentation using OpenAPI (Swagger) to ensure clarity and ease of use for developers.
