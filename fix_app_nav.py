import os

filepath = r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo\navigation\AppNavigation.kt'
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace('package com.jthl.base.navigation', 'package com.jthl.aidemo.navigation')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

# Update MainActivity import
main_filepath = r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo\MainActivity.kt'
with open(main_filepath, 'r', encoding='utf-8') as f:
    main_content = f.read()

main_content = main_content.replace('import com.jthl.base.navigation.AppNavigation', 'import com.jthl.aidemo.navigation.AppNavigation')

with open(main_filepath, 'w', encoding='utf-8') as f:
    f.write(main_content)
