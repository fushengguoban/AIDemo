import os

filepath = r'e:\LearnDemo\AIDemo\base\src\main\java\com\jthl\base\network\NetworkModule.kt'
with open(filepath, 'r', encoding='utf-8') as f:
    lines = f.readlines()

new_lines = []
skip = False
for line in lines:
    if "import com.jthl.aidemo.feature.login.LoginApiService" in line:
        continue
    if "import com.jthl.aidemo.feature.mine.UserApiService" in line:
        continue
    if "val loginApiService: LoginApiService by lazy" in line:
        skip = True
        continue
    if "val userApiService: UserApiService by lazy" in line:
        skip = True
        continue
        
    if skip and "}" in line:
        skip = False
        continue
    elif skip:
        continue
        
    new_lines.append(line)

with open(filepath, 'w', encoding='utf-8') as f:
    f.writelines(new_lines)

# Fix Repositories in app module
login_repo = r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo\feature\login\LoginRepository.kt'
with open(login_repo, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('NetworkModule.loginApiService', 'NetworkModule.retrofit.create(LoginApiService::class.java)')
with open(login_repo, 'w', encoding='utf-8') as f:
    f.write(content)

user_repo = r'e:\LearnDemo\AIDemo\app\src\main\java\com\jthl\aidemo\feature\mine\UserRepository.kt'
with open(user_repo, 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('NetworkModule.userApiService', 'NetworkModule.retrofit.create(UserApiService::class.java)')
with open(user_repo, 'w', encoding='utf-8') as f:
    f.write(content)

