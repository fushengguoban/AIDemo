import os

replacements = {
    "package com.jthl.aidemo.ui.base": "package com.jthl.aidemo.core.base",
    "import com.jthl.aidemo.ui.base": "import com.jthl.aidemo.core.base",
    "package com.jthl.aidemo.ui.theme": "package com.jthl.aidemo.core.theme",
    "import com.jthl.aidemo.ui.theme": "import com.jthl.aidemo.core.theme",
    "package com.jthl.aidemo.navigation": "package com.jthl.aidemo.core.navigation",
    "import com.jthl.aidemo.navigation": "import com.jthl.aidemo.core.navigation",
    "package com.jthl.aidemo.data.util": "package com.jthl.aidemo.core.network",
    "import com.jthl.aidemo.data.util": "import com.jthl.aidemo.core.network",
    "package com.jthl.aidemo.data.remote": "package com.jthl.aidemo.core.network",
    "import com.jthl.aidemo.data.remote.NetworkModule": "import com.jthl.aidemo.core.network.NetworkModule",
    "import com.jthl.aidemo.data.remote.LoginApiService": "import com.jthl.aidemo.feature.login.LoginApiService",
    "import com.jthl.aidemo.data.remote.UserApiService": "import com.jthl.aidemo.feature.mine.UserApiService",
    "import com.jthl.aidemo.data.repository.LoginRepository": "import com.jthl.aidemo.feature.login.LoginRepository",
    "import com.jthl.aidemo.data.repository.RealLoginRepository": "import com.jthl.aidemo.feature.login.RealLoginRepository",
    "import com.jthl.aidemo.data.repository.UserRepository": "import com.jthl.aidemo.feature.mine.UserRepository",
    "import com.jthl.aidemo.data.repository.RealUserRepository": "import com.jthl.aidemo.feature.mine.RealUserRepository",
    "package com.jthl.aidemo.ui.": "package com.jthl.aidemo.feature.",
    "import com.jthl.aidemo.ui.": "import com.jthl.aidemo.feature."
}

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        
    original = content
    for old, new in replacements.items():
        content = content.replace(old, new)
        
    # special fixes
    if "LoginApiService" in filepath or "LoginRepository" in filepath:
        content = content.replace("package com.jthl.aidemo.core.network", "package com.jthl.aidemo.feature.login")
        content = content.replace("package com.jthl.aidemo.data.repository", "package com.jthl.aidemo.feature.login")
    if "UserApiService" in filepath or "UserRepository" in filepath:
        content = content.replace("package com.jthl.aidemo.core.network", "package com.jthl.aidemo.feature.mine")
        content = content.replace("package com.jthl.aidemo.data.repository", "package com.jthl.aidemo.feature.mine")
        
    if original != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Updated {filepath}")

for root, _, files in os.walk(r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo'):
    for file in files:
        if file.endswith('.kt'):
            process_file(os.path.join(root, file))

