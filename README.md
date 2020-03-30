## Lifull Item Decoration

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [License](#license)

## Requirements
* Android API level 14 to use

## Getting Started
```gradle:{root}/build.gradle
allprojects {
    repositories {
        google()
        jcenter()

        maven {
            url 'https://lifull-dev.github.io/lifull-item-decorator/repository/'
        }
    }
}
```

```gradle:{project}/build.gradle
dependencies {
    implementation 'com.lifull.android:item-decoration:1.0.0'
}
```

## License
Copyright 2020 Lifull., co Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.