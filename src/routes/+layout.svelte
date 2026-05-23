<script lang="ts">
  import "./app.css";
  import DeviceStatus from "$lib/components/DeviceStatus.svelte";
  import { ModeWatcher } from "mode-watcher";
  import { goto } from "$app/navigation";
  import { navigating } from "$app/state";
  import { onMount } from "svelte";
  import { getUserInstalledApps } from "tauri-plugin-android-utils-api";

  let { children } = $props();

  function handleKeyDown(event: KeyboardEvent) {
    if (event.key === "ArrowUp" && !navigating.to) {
      goto("/apps");
    }
  }

  onMount(async () => {
    const apps = await getUserInstalledApps()
    console.log(`all apps: ${apps}`);
  });
</script>

<svelte:window onkeydown={handleKeyDown} />

<ModeWatcher />

<div class="relative h-screen w-full overflow-hidden bg-black">
  <div
    class="absolute inset-0 scale-105 bg-[url(https://wallpapers.com/images/hd/cherry-blossom-and-lamps-y8rcho54bc5ou9f4.jpg)] bg-cover bg-center blur-xs"
  ></div>

  <div class="relative z-10 h-full w-full pointer-events-none">
    <div class="absolute top-4 right-10 pointer-events-auto">
      <DeviceStatus />
    </div>

    <div class="h-full w-full pointer-events-auto">
      {@render children()}
    </div>
  </div>
</div>
