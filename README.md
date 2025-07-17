# Github User

## Project Interview

## Set up Instructions

## App Architecture and Tech Stack
```mermaid
graph TD
    app --> features_home
    app --> features_repo
    app --> core_util
    app --> core_presentation

    features_home --> network
    features_home --> core_util
    features_home --> core_presentation
    features_home --> core_domain

    features_repo --> network
    features_repo --> core_util
    features_repo --> core_presentation
    features_repo --> core_data
    features_repo --> core_domain

    core_presentation --> core_data
    core_domain --> core_util
    core_data --> core_domain
    core_data --> network
```

## Feature Implemented


## Design Decisions


## Known Issues/Limitations



## ScreenShots


## Libraries


## Unit Test
