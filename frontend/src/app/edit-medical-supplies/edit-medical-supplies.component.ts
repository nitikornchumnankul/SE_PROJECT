import { Component, OnInit } from '@angular/core';
import { MedicalsuppliesService } from '../medicalsupplies.service';
import { HttpClient } from '@angular/common/http';
import { MatDialog, MatDialogConfig, MatDialogRef } from "@angular/material";
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-medical-supplies',
  templateUrl: './edit-medical-supplies.component.html',
  styleUrls: ['./edit-medical-supplies.component.css']
})
export class EditMedicalSuppliesComponent implements OnInit {
MedicalSupplies: Array<any>;
MedicalInstrument:Array<any>
Useability:Array<any>;
view: any={
  medicalSupplies:null,
  medicalInstrument:null,
  useability:null
}
medicalsuppliesId=this.medicalsuppliesService.getIdMedicalSupplies();
  constructor(private medicalsuppliesService:MedicalsuppliesService, private httpClient:HttpClient ,private dialog: MatDialog,private router: Router ,
              public dialogRef: MatDialogRef<EditMedicalSuppliesComponent>) { }

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
onUpdate(){
    console.log(this.medicalsuppliesId);
           this.httpClient.put('http://localhost:8080/MedicalSupplies/'+ this.medicalsuppliesId +'/'+ this.view.medicalSupplies + '/'+ this.view.medicalInstrument +'/'+ this.view.useability ,this.view)
           .subscribe
           (
             data =>{
             alert('อัพเดตเรียบร้อย');
             console.log('Post Request is seccessful',data);
             this.router.navigate(['/reload/medicalSupplies']);
           },
           error=>{
             console.log('Rrror',error);
             alert('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลให้ครบถ้วน');
           }
           );
           this.dialogRef.close();
         }
onCancel(){
this.dialogRef.close();
}
}
