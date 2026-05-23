<script lang="ts">
  import AppCard from "$lib/components/AppCard.svelte";
  import { onMount } from "svelte";
  import {
    getUserInstalledApps,
    type AppInfo,
  } from "tauri-plugin-android-utils-api";

  let applist = $state<AppInfo[]>([]);
  let loading = $state(true);
  let error = $state<string | null>(null);

  onMount(async () => {
    try {
      applist = await getUserInstalledApps();
      applist.sort((a, b) => a.appName.localeCompare(b.appName));
    } catch (e) {
      error = e instanceof Error ? e.message : String(e);
    } finally {
      loading = false;
    }
  });
</script>

<div class="h-full w-full overflow-y-auto p-8 text-white">
  <h1 class="mb-6 text-sm opacity-70">App menu</h1>

  {#if loading}
    <p class="opacity-70">Loading...</p>
  {:else if error}
    <p class="text-red-400">Error: {error}</p>
  {:else}
    <div class="grid grid-cols-6 gap-4">
      {#each applist as app (app.packageName)}
        <AppCard {app} />
      {/each}
    </div>
  {/if}
</div>