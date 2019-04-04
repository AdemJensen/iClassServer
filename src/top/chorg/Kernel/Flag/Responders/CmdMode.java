package top.chorg.Kernel.Flag.Responders;

import top.chorg.Kernel.Flag.FlagResponder;
import top.chorg.System.Global;
import top.chorg.System.Sys;

public class CmdMode extends FlagResponder {

    @Override
    public int response() {
        if (Global.varExists("GUI_MODE_MODIFIED")) {
            Sys.warn(
                    "Flags",
                    "The display mode have been set once! Rewriting old configuration."
            );
        }
        Global.setVar("GUI_MODE", false);
        Global.setVar("GUI_MODE_MODIFIED", true);
        return 0;
    }

    @Override
    public String getManual() {
        return "Include this flag to enable the cmd mode.";
    }

    @Override
    public void aftActions() {
        Global.dropVar("GUI_MODE_MODIFIED");
    }
}