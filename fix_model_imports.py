import os

replacements = {
    "import com.jthl.aidemo.data.model.LoginRequest": "import com.jthl.aidemo.feature.login.LoginRequest",
    "import com.jthl.aidemo.data.model.LoginResponse": "import com.jthl.aidemo.feature.login.LoginResponse",
    "import com.jthl.aidemo.data.model.UserResponse": "import com.jthl.aidemo.feature.mine.UserResponse"
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

for root, _, files in os.walk(r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo'):
    for file in files:
        if file.endswith('.kt'):
            process_file(os.path.join(root, file))

