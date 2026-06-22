import os

replacements = {
    "package com.jthl.aidemo.core.base": "package com.jthl.base.base",
    "import com.jthl.aidemo.core.base": "import com.jthl.base.base",
    "package com.jthl.aidemo.core.network": "package com.jthl.base.network",
    "import com.jthl.aidemo.core.network": "import com.jthl.base.network",
    "package com.jthl.aidemo.core.navigation": "package com.jthl.base.navigation",
    "import com.jthl.aidemo.core.navigation": "import com.jthl.base.navigation",
}

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        
    original = content
    for old, new in replacements.items():
        content = content.replace(old, new)
        
    if original != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Updated {filepath}")

for root, _, files in os.walk(r'e:\LearnDemo\AIDemo'):
    if '.git' in root or '.gradle' in root or 'build' in root or '.agent' in root:
        continue
    for file in files:
        if file.endswith('.kt'):
            process_file(os.path.join(root, file))

