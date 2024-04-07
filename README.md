# AndroidTemplate > Runtime Permissions
Runtime Permissions contains code samples and runtime permission implementation for android applications. 
[Medium Post](https://attilaakinci.medium.com/dealing-with-android-runtime-permissions-with-jetpack-compose-899148f83a5c)

> [!NOTE]
> Builded upon [AndroidTemplate Core](https://github.com/AttilaAKINCI/AndroidTemplate) 

## App Video
    Single Permission   Multiple Permission
<img src="https://github.com/AttilaAKINCI/AndroidTemplate/assets/21987335/402538d9-4892-4045-9c53-cf29aca05d68" width="160"/> <img src="https://github.com/AttilaAKINCI/AndroidTemplate/assets/21987335/84c2e110-abff-4d03-8482-023e1dd9cfbd" width="160"/>

## Coverage
Runtime Permissions covers points and functionalities below:
* App should request native runtime permission when main action is triggered
* If permission is declined before, rationale dialog should appear
* Rationale should have a functionlity to navigate user to app settings (to cover manual permission update)
* If user returns from app settings, app screen should update itself automatically considering last recent permission state
* If permission is granted, ui should be updated or necessary actions should be taken


## Tech stack of "Runtime Permissions" POC
* [Android Template Core](https://github.com/AttilaAKINCI/AndroidTemplate?tab=readme-ov-file#tech-stack-base-of-white-labelled-app-core)
* Accompanist Permission

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
