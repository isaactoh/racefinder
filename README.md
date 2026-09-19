# Racefinder

**Find certified races and discover your next opportunity to run a qualifying time.**

**Racefinder** is a race discovery platform that aggregates events from the **World Athletics Event Calendar**, enriches them with geographic data, and makes them searchable on an interactive map.

Whether you're chasing a qualifying standard, planning your racing calendar, or simply looking for your next race, Racefinder helps you find the right event.


## Features

### 🔄 Automatic Race Ingestion
Race data is automatically collected from the World Athletics Event Calendar on a scheduled basis, keeping the race database synchronised with the latest available event information.

### 🗺️ Geographic Race Discovery
Racefinder converts race locations into geographic coordinates using the **Google Maps Geocoding API**.

Geocoded locations are cached, allowing races to be displayed accurately on an interactive map while keeping geocoding costs and API usage under control

### 🔎 Search & Filtering
Find your ideal race by filtering by:
* Event
* Country
* Date range

## Architecture
```mermaid
flowchart TD

subgraph group_api["Race Discovery API"]
  node_race_controller["Race Controller"]
  node_race_service["Race Service<br/>[RaceService.java]"]
end

subgraph group_ingestion["Race Ingestion"]
  node_ingestion_scheduler["Ingestion Scheduler"]
  node_ingestion_service["Ingestion Service"]
  node_wa_event_client["Event Ingestion"]
  node_wa_api_client["World Athletics Client"]
  node_location_extractor["Location Extractor<br/>[Extractor.java]"]
end

subgraph group_geocoding["Geographic Enrichment"]
  node_geocoding_service["Geocoding Service"]
  node_google_geocoding_client["Google Geocoding Client"]
  node_google_api_client["Google API Client"]
end

subgraph group_storage["Persistence"]
  node_race_repository["Race Repository"]
  node_geocode_cache_repository["Geocode Cache"]
  node_race_database[("PostgreSQL / Supabase")]
end

node_race_user(("Race User"))
node_world_athletics{{"World Athletics API"}}
node_google_maps{{"Google Maps API"}}

node_race_user -->|"requests races"| node_race_controller
node_race_controller -->|"invokes search"| node_race_service
node_race_service -->|"queries races"| node_race_repository
node_race_repository -->|"reads races"| node_race_database
node_race_service -->|"returns races"| node_race_controller
node_race_controller -->|"serves results"| node_race_user
node_ingestion_scheduler -->|"triggers daily"| node_ingestion_service
node_ingestion_service -->|"starts ingestion"| node_wa_event_client
node_wa_event_client -->|"fetches events"| node_wa_api_client
node_wa_api_client -->|"queries calendar"| node_world_athletics
node_wa_event_client -->|"extracts country"| node_location_extractor
node_wa_event_client -->|"geocodes venue"| node_geocoding_service
node_geocoding_service -->|"checks cache"| node_geocode_cache_repository
node_geocode_cache_repository -->|"reads cache"| node_race_database
node_geocoding_service -->|"geocodes missing"| node_google_geocoding_client
node_google_geocoding_client -->|"requests coordinates"| node_google_api_client
node_google_api_client -.->|"calls geocoding"| node_google_maps
node_geocoding_service -->|"saves coordinates"| node_geocode_cache_repository
node_geocode_cache_repository -->|"writes cache"| node_race_database
node_wa_event_client -->|"saves races"| node_race_repository
node_race_repository -->|"writes races"| node_race_database

click node_race_controller "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/controller/RaceController.java"
click node_race_service "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/race/RaceService.java"
click node_ingestion_scheduler "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/ingestion/IngestionScheduler.java"
click node_ingestion_service "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/ingestion/IngestionService.java"
click node_wa_event_client "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/ingestion/worldathletics/WorldAthleticsEventClient.java"
click node_wa_api_client "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/ingestion/worldathletics/WorldAthleticsApiClient.java"
click node_location_extractor "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/ingestion/worldathletics/Extractor.java"
click node_geocoding_service "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/geocoding/GeocodingService.java"
click node_google_geocoding_client "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/geocoding/google/GoogleGeocodingClient.java"
click node_google_api_client "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/geocoding/google/GoogleApiClient.java"
click node_race_repository "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/race/RaceRepository.java"
click node_geocode_cache_repository "https://github.com/isaactoh/racefinder/blob/main/src/main/java/com/isaactoh/racefinder/geocoding/google/GeocodeCacheRepository.java"

classDef toneNeutral fill:#f8fafc,stroke:#334155,stroke-width:1.5px,color:#0f172a
classDef toneBlue fill:#dbeafe,stroke:#2563eb,stroke-width:1.5px,color:#172554
classDef toneAmber fill:#fef3c7,stroke:#d97706,stroke-width:1.5px,color:#78350f
classDef toneMint fill:#dcfce7,stroke:#16a34a,stroke-width:1.5px,color:#14532d
classDef toneRose fill:#ffe4e6,stroke:#e11d48,stroke-width:1.5px,color:#881337
classDef toneIndigo fill:#e0e7ff,stroke:#4f46e5,stroke-width:1.5px,color:#312e81
classDef toneTeal fill:#ccfbf1,stroke:#0f766e,stroke-width:1.5px,color:#134e4a
class node_race_controller,node_race_service,node_race_user toneBlue
class node_ingestion_scheduler,node_ingestion_service,node_wa_event_client,node_wa_api_client,node_location_extractor toneAmber
class node_geocoding_service,node_google_geocoding_client,node_google_api_client,node_world_athletics,node_google_maps toneMint
class node_race_repository,node_geocode_cache_repository,node_race_database toneRose
```

## Tech Stack

| Component | Technology |
| :---: | :---: |
| Backend | Spring Boot |
| Database | PostgreSQL / Supabase |
| Data | World Athletics API / GraphQL|
| Geocoding | Google Maps Geocoding API |
| Frontend | *Coming Soon* |

## Demo
**Coming Soon!**
