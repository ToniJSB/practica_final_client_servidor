<template>

    <q-card dark bordered class="bg-grey-9 my-card">
      <q-card-section>
        <div class="text-h6"> {{title}} </div>
        <div class="text-subtitle2"> {{author}} </div>
      </q-card-section>

      <q-separator dark inset />

      <q-card-section>
        {{content}}
      <q-separator dark inset />
        {{ date }}

      </q-card-section>
    </q-card>

</template>

<script>
export default {
  name: 'PostList',
  props: {
    title:{
        type: String,
    },
    content:{
        type: String,
    },
    date:{
        type: String,
    },
    author:{
        type: String,
    }
}
}
</script>
