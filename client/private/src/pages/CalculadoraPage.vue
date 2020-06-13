<template>
  <div class="q-pa-md">
    <div class="q-gutter-y-md column" style="max-width: 300px">
     <q-input outlined placeholder="peso en Kg" v-model="peso" />
     <q-input outlined placeholder="altura en Cm" v-model="altura" />
     <q-input outlined placeholder="edad" v-model="edad" />
     <q-select outlined v-model="sexo" label="sexo" :options="sexoOptions"/>
     <q-select outlined v-model="actividad" label="actividad" :options="tipoActividad"/>

     <q-btn @click="calculate" label="calcular"/>

    <q-card dark bordered class="bg-grey-9 my-card">
      <q-card-section>
        <div class="text-subtitle2"> Con los datos proporcionados podrás consumir </div>
      <q-separator dark inset />
        {{calorias}} Kcal
      </q-card-section>
    </q-card>

    <div @dragenter="onDragEnter" @dragleave="onDragLeave" @dragover="onDragOver" class="drop-target rounded-borders "/>
    </div>
      <div v-mutation="captador" @dragenter="onDragEnter" @dragleave="onDragLeave" @dragover="onDragOver" @drop="onDrop" class="drop-target rounded-borders overflow-hidden"/>
    </div>

    </div>
  </div>
</template>

<script>
export default {
    name: 'CalculadoraPage',
    data() {
        return {
            peso : '',
            altura : '',
            edad : '',
            sexo : null,
            sexoOptions : [
            {
                value:'M',
                label:'Mujer'
            },
            {
                value:'H',
                label:'Hombre'
            }
            ],
            actividad:null,
            tipoActividad:[
                {
                    value: 'poco',
                    label: 'poco o ninguno'
                },
                {
                    value: 'leve',
                    label: 'ejercicio leve (1-3 días por semana)'
                },
                {
                    value: 'moderado',
                    label: 'ejercicio moderado (3-5 días por semana)'
                },
                {
                    value: 'fuerte',
                    label: 'ejercicio fuerte (6 días por semana)'
                },
                {
                    value: 'profesional',
                    label: 'ejercicio profesional o extremo'
                },
            ],
            calorias:0
        }
    },
    methods:{
        captador(){

        },
        calculate(){
            if (this.peso == '' || this.altura == '' || this.edad == '' || this.sexo == null || this.actividad == null){
                console.log(this.peso)
                console.log(this.altura)
                console.log(this.edad)
                console.log(this.sexo)
                console.log(this.actividad)
            }else{
                let tmb = (this.peso*10)+(this.altura*6.25)-(5*this.edad);

                if (this.sexo.value==="H"){
                    console.log('hombre')
                    tmb = tmb+5;
                }else{
                    tmb = tmb-161;
                }
                let caloriasDiarias;
                switch(this.actividad.value){
                    case 'poco':
                        console.log(this.actividad)
                        caloriasDiarias = tmb * 1.2;
                        break;
                    case 'leve':
                        console.log(this.actividad)
                        caloriasDiarias = tmb * 1.375;
                        break;
                    case 'moderado':
                        console.log(this.actividad)
                        caloriasDiarias = tmb * 1.55;
                        break;
                    case 'fuerte':
                        console.log(this.actividad)
                        caloriasDiarias = tmb * 1.725;
                        break;
                    case 'profesional':
                        console.log(this.actividad)
                        caloriasDiarias = tmb * 1.9;
                        break;
                    
                }
                this.calorias = caloriasDiarias
                console.log(tmb)
                console.log(caloriasDiarias)

            }

        },

        // store the id of the draggable element
        onDragStart (e) {
        e.dataTransfer.setData('text', e.target.id)
        e.dataTransfer.dropEffect = 'move'
        },

        onDragEnter (e) {
        // don't drop on other draggables
        if (e.target.draggable !== true) {
            e.target.classList.add('drag-enter')
        }
        },

        onDragLeave (e) {
        e.target.classList.remove('drag-enter')
        },

        onDragOver (e) {
        e.preventDefault()
        },

        onDrop (e) {
            e.preventDefault()

            // don't drop on other draggables
            if (e.target.draggable === true) {
                return
            }

            const draggedId = e.dataTransfer.getData('text')
            const draggedEl = document.getElementById(draggedId)

            // check if original parent node
            if (draggedEl.parentNode === e.target) {
                e.target.classList.remove('drag-enter')
                return
            }

            // make the exchange
            draggedEl.parentNode.removeChild(draggedEl)
            e.target.appendChild(draggedEl)
            e.target.classList.remove('drag-enter')
        }
    
    }


}
</script>