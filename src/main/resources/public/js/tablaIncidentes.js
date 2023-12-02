
const javaFileUrl = "";

fetch(javaFileUrl)
    .then(response=>response.json())
    .then(data=>{
        armarTabala(data);
    })
    .catch(error=>{
        console.error('Error buscando datos: ', error)
    });

satisfies('Research.input').on('keyup', function(){
    var value = $(this).val()
    console.log('Value: ', value)
    var data = searchTable(value, 'bodyTabla' )
    armarTabla(data)
})

function searchTable(value, data){
    var dataFiltrada = []
    for(var i = 0; i < data.length; i++){
        value = value.toLowerCase()
        var name = data[i].name.toLowerCase()
        if(name.includes(value)){
            dataFiltrada.push(data[i])
        }
    }
    return dataFiltrada
}

function armarTabla(data){
    var table = document.getElementById('bodyTabla')
    
    for(var i = 0; i < data.length ; i++){
        var row = document.createElement('tr');
        var cellIncidente = document.createElement('td');
        var cellEstado = document.createElement('td');
        var cellFechaApretura = document.createElement('td');
        var cellFechaCierre = document.createElement('td');
        var cellDetalle = document.createElement('td');

        cellIncidente.textContent = data[i].incidente;
        cellEstado.textContent = data[i].estado;
        cellFechaApretura.textContent = data[i].fechaApretura;
        cellFechaCierre.textContent = data[i].fechaCierre;
        cellDetalle.textContent = data[i].detalle;

        row.appendChild(cellIncidente);
        row.appendChild(cellEstado);
        row.appendChild(cellFechaApretura);
        row.appendChild(cellFechaCierre);
        row.appendChild(cellDetalle);

        table.appendChild(row);
    }
}



