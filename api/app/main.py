from typing import Union
from pydantic import BaseModel
from fastapi import FastAPI, status
import json
from pathlib import Path

app = FastAPI()

class Task(BaseModel):
   id: int
   name: str

# タスク取得
@app.get("/tasks")
def get_task():
   json_file_path = Path(__file__).parent / "task.json"

   with open(json_file_path, "r") as file:
      data = json.load(file)
      
      return data

# タスク追加
@app.post("/add/task", status_code=status.HTTP_201_CREATED)
def add_task(task: Task):
   json_file_path = Path(__file__).parent / "task.json"

   with open(json_file_path, "r") as file:
      data = json.load(file)
   
   data["test"].append({"id": task.id, "name": task.name})
   jason_data = json.dumps(data)

   with open(json_file_path, "w") as f:
      f.write(jason_data)

   return{"message": "success"}
   
# タスク削除
@app.post("/del/task/{id}", status_code=status.HTTP_201_CREATED)
def del_task(id: int, task: Task):
   json_file_path = Path(__file__).parent / "task.json"

   with open(json_file_path, "r") as file:
      data = json.load(file)
   
   list = data["test"]

   for index, value in enumerate(list):
      if (value["id"] == id):
         del list[index]

   data["test"] = list
   jason_data = json.dumps(data)

   with open(json_file_path, "w") as f:
      f.write(jason_data)

   return{"message": "success"}
