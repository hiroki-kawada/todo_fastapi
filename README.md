# ToDoFastAPI

## 目的
 - Pythonの基礎の学習を行なったため学習の成果を残すのが目的。
 - 今回は、普段モバイルアプリの開発を主に行なっているためFastAPIでAPIを作成しモバイルアプリとAPI通信可能にすることを目標として開発。

## アプリ側の説明
 - 現状、APIで受け取った情報をそのまま表示するだけ（※あくまでPython学習の成果を残すためなのでアプリ側は今回は簡単なものにする。）

## FastAPI側
 - jsonファイルからリスト型のデータを取得、追加、削除を行えるように実装。
 - FastAPIのほかデプロイを簡単にするためDockerも使用しています。

### FastAPIとは
 - Pythonで記述されたモダンで高速なWebフレームワーク。
 - 公式ドキュメントが日本語にも対応し簡単に利用できるためこちらを採用。
 - 非同期I/Oを活用して、パフォーマンスを最適化することも可能。（※今回は使用してません。）
 - "http://localhost:8000/docs"を使用することで全てのエンドポイント、メソッドなどが確認可能になります。

### Dockerとは
 - アプリケーションをその依存関係と共にコンテナという単位でパッケージ化し、どこでも一貫して動作させるためのツール。
 - Dockerを使用すれば環境に左右されず動作の一貫性を保つことができるため他者の環境でも動作確認が可能。
 - ローカル環境でテストが可能であることやDocker環境を業務でも使用している部分があるのでこれを機に基礎だけでも学習する必要があると思い使用しました。

### API側のコード説明
```
from fastapi import FastAPI
app = FastAPI()
```
 - こちらのコードでFastAPIを読み込み、インスタンスを作成しています。
 - これでFastAPIの機能が使えるようになります。

```
# タスク取得
@app.get("/tasks")
def read_root():
   json_file_path = Path(__file__).parent / "task.json"

   with open(json_file_path, "r") as file:
      data = json.load(file)
      
      return data
```
 - 「@app.get」はPythonのデコレータでFastAPIに対して、その下の関数がリクエスト処理を担当
 - ルートを指定するデコレータでもあり
 - 今回は「/tasks」を指定しているのでブラウザ上で「http:(IPアドレス)/tasks」でアクセスすることが可能になります。

### Docker側の説明
```
Dockerfileの中身

# ベースイメージ
FROM python:3.9

# コンテナ内のでの作業ディレクトリの設定
WORKDIR /app

# 要件が書かれたファイルコピー（基本的にpip installするライブラリ名を記述したファイル）
COPY requirements.txt .

# 要件ファイル（requirements）にあるパッケージの依存関係をインストールします。
RUN pip install --no-cache-dir --upgrade -r /app/requirements.txt

# appディレクトリをコピー（最も頻繁に変更されるコードが含まれる部分をコピー）
# 最も頻繁に変更されるコードが含まれているためDockerのキャッシュはこれ以降のステップに簡単に使用されないため最後に記載
COPY . .

# uvicornサーバーを実行するためのコマンドを設定
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8080"]

```
 - Dockerコンテナを作成するための設計やスクリプトの役割を果たします。
 - 「FROM python:3.9」はベースイメージを指定しています。
 - 今回はFastAPIの環境が動けば良いのでpythonが動く環境をベースイメージにする必要があるので「python:3.9」を指定してます。
 - 「WORKDIR」で作業ディレクトリを指定します。
 - ここで指定したディレクトリを基準にして「RUN」「COPY」のコマンドが実行されます。
 - 「COPY requirements.txt .」はコンテナに指定したファイルをコピーするコマンドになります。
 - 「requirements.txt」は「pip install」するライブラリを記載してあるファイルになります。
 - 「RUN pip install --no-cache-dir --upgrade -r /app/requirements.txt」はコピーしたファイルをもとに「pip install」コマンドが実行されます。
 - 実行時には「--no-cache-dir」でローカルに保存しないようにpipに指示し「--upgrade」で既にインストールされているパッケージにアップグレードするように指示しています。
 - 「COPY . .」 コマンドでホストマシンのカレントディレクトリの内容がすべてappディレクトリに保存します。
 - 最初にコピーすれば問題なさそうですがキャッシュ効率の悪化、不要ファイル管理などがあり基本的には依存関係先をコピーしてから行うのがベストプラクティスになってます。
  - 「CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8080"]
」のコマンドでFastAPIを実行するコマンドが実行されDocker環境でAPIが起動することができます。
 - 「CMD」以外の[]で囲まれた部分はFastAPIを実行するときのコマンドになります。



