import { Component, OnInit } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css'],  // Make sure this path is correct
})
export class TaskComponent implements OnInit {
  tasks: Task[] = [];
  newTask: Task = { title: '', description: '', status: '', id: '' };
  isTaskModalOpen: boolean = false; // Flag to control modal visibility

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getAllTasks().subscribe(
      (data: Task[]) => {
        this.tasks = data;
      },
      (error) => {
        console.error('Error fetching tasks', error);
      }
    );
  }

  // Open the task creation modal (pop-up)
  openTaskModal(): void {
    this.isTaskModalOpen = true;
  }

  // Close the task creation modal (pop-up)
  closeTaskModal(): void {
    this.isTaskModalOpen = false;
    this.newTask = { title: '', description: '', status: '', id: '' }; // Reset form
  }

  // Add a new task
  addTask(): void {
    if (!this.newTask.title || !this.newTask.description || !this.newTask.status) {
      alert('Please fill in all fields.');
      return;
    }

    const taskToCreate = {
      title: this.newTask.title,
      description: this.newTask.description,
      status: this.newTask.status
    };

    this.taskService.createTask(taskToCreate).subscribe(
      (createdTask: Task) => {
        this.tasks.push(createdTask);
        this.closeTaskModal(); // Close the modal after the task is created
        alert('✅ Task added successfully!');
      },
      (error) => {
        console.error('Error adding task:', error);
        alert('❌ Failed to add task.');
      }
    );
  }

  viewTask(task: Task): void {
    alert(`📄 Viewing Task:\n\n📝 Title: ${task.title}\n📌 Description: ${task.description}`);
  }

  updateTask(task: Task): void {
    const updatedStatus = prompt("Enter new status for the task:", task.status);

    if (updatedStatus !== null) {
      const updatedTask = { ...task, status: updatedStatus };

      fetch(`http://localhost:8080/tasks/${task.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedTask),
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to update task');
        }
        return response.json();
      })
      .then(data => {
        alert(`✅ Task updated: ${data.title}, Status: ${data.status}`);
      })
      .catch(error => {
        console.error('Error updating task:', error);
        alert("❌ Failed to update task.");
      });
    }
  }

  deleteTask(id: string): void {
    if (confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(id).subscribe(
        () => {
          this.tasks = this.tasks.filter(t => t.id !== id);
        },
        (error) => {
          console.error('Error deleting task:', error);
        }
      );
    }
  }
}
