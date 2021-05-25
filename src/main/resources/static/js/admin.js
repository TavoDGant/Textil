console.log(empleado);
const empleados = empleado.body;

const tabla = document.querySelector("#miTabla tbody");

function listaEmpleados(data) {
    for (let i = 0; i < data.length; i++) {
        const row = document.createElement('tr');
        const nombre = data[i].nombre + ' ' + data[i].ape_pat + ' ' + data[i].ape_mat;
        const fecha = new Date(data[i].nacimiento);
        const diff_ms = Date.now() - fecha.getTime();
        const age_dt = new Date(diff_ms);
        const edad = Math.abs(age_dt.getUTCFullYear() - 1970);
        const cortes = data[i].cortes;
        let totalCortes = 0;
        let sueldoTotal = 0;        
        for (let j = 0; j < cortes.length; j++) {
            totalCortes += cortes[j].cortes;
            sueldoTotal += cortes[j].sueldo;    
        }
        row.innerHTML += `
            <td>${nombre}</td>
            <td>${edad}</td>
            <td>${totalCortes}</td>
            <td>$ ${sueldoTotal}</td>
            <td> <a href="#"> <i class="fa fa-info-circle fa-2x info" aria-hidden="true"></i></a> </td>
        `;
        tabla.appendChild(row);
    }
}
listaEmpleados(empleados);

/*function cortesMesAnterior(data){
    for (let i = 0; i < data.length; i++){
        for (let j = 0; j < cortes.length; j++) {
             
        }
    }
}
cortesMesAnterior(empleados);
*/


function buscarTabla() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("buscarTabla");
    filter = input.value.toUpperCase();
    table = document.getElementById("miTabla");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

var xValues = [3,4,5,6,7,8,10,11,12,13,14,15];
var yValues = [5000,4580,5124,4592,3597,4656,5011,4671,4234,4985,4871,5021];

new Chart("myChart", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(0,0,255,1.0)",
      borderColor: "rgba(0,0,255,0.1)",
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    scales: {
      yAxes: [{ticks: {min: 1000, max:9000}}],
    }
  }
});