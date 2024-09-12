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
