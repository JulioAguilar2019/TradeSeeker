package sv.edu.catolica.project_final;

import java.io.Serializable;
import java.util.Date;

import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkerModel;

public class ListWorker implements Serializable {
    public WorkModel work;
    public WorkerModel worker;

    public ListWorker(WorkModel work, WorkerModel worker) {
        this.work = work;
        this.worker = worker;
    }

    public WorkModel getWork() {
        return work;
    }

    public void setWork(WorkModel work) {
        this.work = work;
    }

    public WorkerModel getWorker() {
        return worker;
    }

    public void setWorker(WorkerModel worker) {
        this.worker = worker;
    }
}
