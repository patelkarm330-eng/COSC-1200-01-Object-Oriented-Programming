
   /*
     * Task.java
     * Author: Karm Patel
     * Date: April 24, 2026
     * Description: Represents a single Task with an ID, name, completion status, and deadline.
     */


    public class task {

        private int id;
        private String name;
        private boolean completed;
        private String deadline;

        public task(int id, String name, boolean completed, String deadline) {
            this.id = id;
            this.name = name;
            this.completed = completed;
            this.deadline = deadline;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) { this.id = id; }

        public String getName() {
            return name;
        }

        public void setName(String name) { this.name = name; }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) { this.completed = completed; }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) { this.deadline = deadline; }


         //  Converts the task into a file‑friendly format.

        public String toFileString() {
            return id + "|" +
                    name.replace("|", "/") + "|" +
                    completed + "|" +
                    deadline.replace("|", "/");
        }

        // Creates a Task object from a line in the file.

        public static task fromFileString(String line) {
            String[] parts = line.split("\\|");
            if (parts.length != 4) return null;

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            boolean completed = Boolean.parseBoolean(parts[2]);
            String deadline = parts[3];

            return new task(id, name, completed, deadline);
        }

        @Override
        public String toString() {
            return "Task #" + id + ": " + name;
        }
    }


