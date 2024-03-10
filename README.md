# AndroidTemplate
AndroidTemplate repository provides modern android app base to build new apps upon it. 

- Master branch -> core & common base logics (white label app base)
- Sub branches contains architectural proof of concepts(POCs) 
  - [Network connectivity](https://github.com/AttilaAKINCI/AndroidTemplate/tree/poc-network-connectivity?tab=readme-ov-file)
  - App Permissions *(Planned, will be added later on)*
  - GPS Locations *(Planned, will be added later on)*

## Tech stack base of "White Labelled App Core"
* Kotlin
* [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
* [Kotlin DSL](https://developer.android.com/build/migrate-to-kotlin-dsl)
* [Material 3](https://m3.material.io/)
* Patterns
    - MVI
    - Clean Architecture
* [JetPack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQiAjMKqBhCgARIsAPDgWlyVg8bZaasX_bdQfYrAXsuDQ6vD-2SmFcTv34Fb-jLQxgGqPD7UxKgaAso5EALw_wcB&gclsrc=aw.ds)
* [Edge to Edge UI design](https://developer.android.com/jetpack/compose/layouts/insets)
* Custom Splash Screen
* Dark/Light UI Mode
* [Compose Destinations](https://github.com/raamcosta/compose-destinations) / [Documentation](https://composedestinations.rafaelcosta.xyz/)
* [Ktor Client](https://ktor.io/docs/client-supported-platforms.html)
* [Gradle Version Catalogs (toml)](https://developer.android.com/build/migrate-to-catalogs)
* [Lottie Animations](https://github.com/airbnb/lottie-android)
* [Coil](https://github.com/coil-kt/coil)
* [Timber Client logging](https://github.com/JakeWharton/timber)
* [Dependency Injection (HILT)](https://developer.android.com/training/dependency-injection/hilt-android)
* [Turbine](https://github.com/cashapp/turbine)
* [MockK](https://mockk.io/)
* Unit testing
* JUnit5

# License

The code is licensed as:

```
Copyright 2021 Attila Akıncı

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
