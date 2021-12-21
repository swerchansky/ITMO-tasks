`include "alu.v"
`include "control_unit.v"
`include "util.v"

module mips_cpu(clk, instruction_memory_a, instruction_memory_rd, data_memory_a, data_memory_rd, data_memory_we, data_memory_wd,
                register_a1, register_a2, register_a3, register_we3, register_wd3, register_rd1, register_rd2);
  
  input clk;
  output data_memory_we;
  output reg [31:0] instruction_memory_a, data_memory_a, data_memory_wd;
  inout [31:0] instruction_memory_rd, data_memory_rd;
  
  output register_we3;
  output reg [4:0] register_a1, register_a2, register_a3;
  output reg [31:0] register_wd3;
  inout [31:0] register_rd1, register_rd2;
  
  output memtoreg, memwrite, branch, alusrc, regdst, regwrite, zero;
  inout [2:0] alucontrol;
  
  output [31:0] signimm, srcb1;
  output [31:0] shlout;
  output reg [31:0] PC, PCtmp, PCPlus4, PCBranch, result;
  output reg PCSrc;
  output reg [31:0] alures;
  output reg [31:0] rdinst;
  
  initial begin
    PC = 0;
  end
  
    assign instruction_memory_a = PC;

    control_unit ctr(instruction_memory_rd[31:26], instruction_memory_rd[5:0], memtoreg, data_memory_we, branch, alusrc, regdst, register_we3, alucontrol);


    assign  register_a1 = instruction_memory_rd[25:21];
    assign  register_a2 = instruction_memory_rd[20:16];


    sign_extend ext(instruction_memory_rd[15:0], signimm);


    mux2_32 mux_alu(register_rd2, signimm, alusrc, srcb1);


    alu al(register_rd1, srcb1, alucontrol, alures, zero);


    assign data_memory_wd = register_rd2;
    assign data_memory_a = alures;


    mux2_32 muxdatamem(alures, data_memory_rd, memtoreg, result);


    assign register_wd3 = result;


    mux2_5 mux_a3(instruction_memory_rd[20:16], instruction_memory_rd[15:11], regdst, register_a3);
  
  assign shlout = signimm;

  adder add4(PC, 32'b00000000000000000000000000000001, PCPlus4);

    adder addBranch(shlout, PCPlus4, PCBranch);


    assign PCSrc = zero & branch;


    mux2_32 muxPC(PCPlus4, PCBranch, PCSrc, PCtmp);

   
  always @(posedge clk) begin
    PC = PCtmp;
  end

  
endmodule
