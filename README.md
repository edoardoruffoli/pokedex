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

- Git
- Docker

### Steps

1. Clone the repository:
   ```shell
   git clone <repository-url>
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

## Improvements for Production

- **Add Persistence:** Implement database support to store Pokemon data locally to reduce external API calls.
- **Add CloudWatch:** Integrate AWS CloudWatch for monitoring API calls and performance metrics.


Add persistence
Add cloudwatch to keep track of api calls

Add jacoco coverage report
Persist pokemon to handle rate limiting constraints
Add cloudwatch to keep track of api calls

Implemented circuit breaker on funTranslations API, maybe we can do it for PokeAPI
