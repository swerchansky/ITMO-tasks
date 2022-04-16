module data_memory(a, we, clk, wd, rd);
  input we, clk;
  input [31:0] a;
  input [31:0] wd;
  output [31:0] rd;

  integer i;
  reg [31:0] memory[0:515];
  
  initial begin
    for (i = 0; i < 516; i = i + 1) begin
    memory[i] = 0;
  	end
  end

  always @(posedge clk) begin
    if (we == 1) begin
      memory[a/4] <= wd;
    end
  end
  
  
  assign rd = memory[a/4];
  
  
endmodule

module instruction_memory(a, rd);
  input [31:0] a;
  output reg [31:0] rd;

  reg [31:0] ram[0:63];

  initial
    begin
      $readmemb("instructions.dat", ram);
    end
  
  always@(a) begin
    rd = ram[a/4];
  end
  

endmodule