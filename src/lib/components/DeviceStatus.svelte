<script>
  import { BluetoothOff, Wifi } from "@lucide/svelte";
  import { getBluetoothStatus } from "tauri-plugin-android-utils-api";


</script>

<div class="flex flex-row gap-5">
  <Wifi size={15} />
  <BluetoothOff size={15} />
</div>
