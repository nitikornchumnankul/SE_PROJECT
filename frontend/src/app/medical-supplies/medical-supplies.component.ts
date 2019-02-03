import { Component, OnInit ,ViewChild} from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs/internal/Observable';
import { MatDialog, MatDialogConfig } from "@angular/material";

import { MedicalsuppliesService } from '../medicalsupplies.service';
import { HttpClient } from '@angular/common/http';
import { EditMedicalSuppliesComponent } from '../edit-medical-supplies/edit-medical-supplies.component';


export interface MedicalSupplies{
medicalsuppliesId:number;
medicalsuppliesName:String;
  medicalInstrument:{
    medicalInstrumentName:String;
  }
  useability:{
    useabilityName:String;
  }
}
export class MatTableDataSource extends DataSource<any>{
  
  constructor(private medicalsuppliesService:MedicalsuppliesService){
    super();
  }
  connect(): Observable<MedicalSupplies[]>{
    return this.medicalsuppliesService.getShow();
  }
  disconnect(){}
}

@Component({
  selector: 'app-medical-supplies',
  templateUrl: './medical-supplies.component.html',
  styleUrls: ['./medical-supplies.component.css']
})
export class MedicalSuppliesComponent implements OnInit {
  displayedColumns: string[] = ['medicalsuppliesId', 'medicalsuppliesName', 'medicalInstrument','useability',"actions"];
  dataSource = new MatTableDataSource(this.medicalsuppliesService);
  drug: Array<any>;//คืออะไร

  @ViewChild(MatPaginator) paginator: MatPaginator;
MedicalSupplies: Array<any>;
MedicalInstrument:Array<any>
Useability:Array<any>;
view: any={
  medicalSupplies:null,
  medicalInstrument:null,
  useability:null
}
  
constructor(private medicalsuppliesService:MedicalsuppliesService, private httpClient:HttpClient ,private dialog: MatDialog ) { }

  ngOnInit() {
    this.medicalsuppliesService.getMedicalSupplies().subscribe(medicalSupplies => {
      this.MedicalSupplies = medicalSupplies;
      console.log(this.MedicalSupplies);
    });
    this.medicalsuppliesService.getMedicalInstrument().subscribe(medicalInstrument => {
      this.MedicalInstrument = medicalInstrument;
      console.log(this.MedicalInstrument);
    });
    this.medicalsuppliesService.getUseability().subscribe(useability => {
      this.Useability = useability;
      console.log(this.Useability);
    });

  }
  onSave(){
    console.log(this.view.medicalSupplies);
    console.log(this.view.medicalInstrument);
    console.log(this.view.useability);
    
    this.httpClient.post('http://localhost:8080/MedicalSupplies/'+ this.view.medicalSupplies + '/'+ this.view.medicalInstrument +
    '/'+ this.view.useability,this.view)
    .subscribe
    (
      data =>{
      alert('บันทึกเรียบร้อย');
      console.log('Post Request is seccessful',data);
      window.location.reload();
    },
    error=>{
      console.log('Rrror',error);
      alert('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลให้ครบถ้วน');
    }
    );
  }
  onDelete(medicalsuppliesId){
    this.httpClient.delete('http://localhost:8080/MedicalSupplies/'+ medicalsuppliesId )
    .subscribe
    (
      data =>{
      alert('ลบเรียบร้อย');
      console.log('Delete Request is seccessful',data);
      window.location.reload();
    },
    error=>{
      console.log('Rrror',error);
      alert('ไม่สามารถลบได้ โปรดกรุณาตรวจสอบข้อมูลอีกครั้ง');
    }
    );
  }
  onEdit(editMedicalsupplies){
    this.medicalsuppliesService.getEditMedicalSupplies(editMedicalsupplies);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(EditMedicalSuppliesComponent,dialogConfig);
  }

}
