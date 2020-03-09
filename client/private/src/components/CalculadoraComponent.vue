<template>

  <div class="q-pa-md">
    <div class="q-gutter-y-md column" style="max-width: 300px">
     <q-input outlined v-model="peso" />
     <q-input outlined v-model="altura" />
     <q-input outlined v-model="edad" />
     <q-select v-model="sexo" :options="sexoOptions"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PostList',
  data() {
      return {
          peso : '',
          altura : '',
          edad : '',
          sexo : null,
          sexoOptions : [
              'Mujer', 'Hombre'
          ]
      }
  }
}

</script>
