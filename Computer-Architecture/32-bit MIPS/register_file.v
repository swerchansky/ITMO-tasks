module register_file(clk, we3, a1, a2, a3, wd3, rd1, rd2);
  input clk, we3;
  input [4:0] a1, a2, a3;
  input [31:0] wd3;
  output [31:0] rd1, rd2;
  
  reg [31:0] rdata [0:31];
  
  integer i;
  initial begin
  for (i = 0; i < 32; i = i + 1) begin
    rdata[i] = 0;
  end
  end
 
  always @(posedge clk) begin
    if (we3 == 1) begin
      rdata[a3] <= wd3;
    end
  end

  assign rd1 = (a1 != 0) ? rdata[a1] : 0;
  assign rd2 = (a2 != 0) ? rdata[a2] : 0;
  
endmodule