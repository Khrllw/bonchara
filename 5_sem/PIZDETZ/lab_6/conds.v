module conds
	(input clk,
	 input reset,
	 input run,
	 input [3:0] balance,
	 output reg [5:0] out);

reg [2:0] state;
reg [1:0] cnt;

parameter Res = 0, 
	  Weit = 1, 
          Read = 2, 
          Ind = 3,  
	  Entr = 4,
	  Fin = 5;

always @ (posedge clk or posedge reset)
begin
	if (reset) state <= Res;
	else 
		begin case (state)
		Res:
			state <= Weit;
		Weit:
			if (cnt==2'd2) state <= Read; //- * 2
		Read: begin
			if (!run) state <= Weit;
			if (cnt==2'd2) state <= Ind; // Read * 2
		end
		Ind:
			if (cnt==2'd3) // (balance - 4) * 3
				if (balance > 3'd4) state <= Entr;
				else state <= Weit;
		Entr:
			if (cnt==2'd4) state <= Weit; // Go * 4
		default:
			state <= Res;
		endcase
	end
end

always @ (posedge clk)
begin
	case (state)
	Res:
		begin
		cnt <= 2'd0;
		out <= 3'b000;
		end
	Weit:
		begin
		if (cnt==2'd2) cnt <= 2'd0;
		else
			cnt <= cnt + 2'd1;
			out <= 6'b010000; // _
		end
	Read:
		begin
		if (cnt==2'd2) cnt <= 2'd0;
		else
			cnt <= cnt + 2'd1;
			out <= 6'b100000; // read
		end
	Ind:
		begin
		if (cnt==2'd3) cnt <= 2'd0;
		else
			cnt <= cnt + 2'd1;
			if (balance > 3'd4) begin 
				out <= 6'd0;
				out <= balance - 3'd4; // balance - 4
			end 
			else out <= 6'd0;
		end
	Entr:
		begin
		if (cnt==2'd4) cnt <= 2'd0;
		else
			cnt <= cnt + 2'd1;
			out <= 6'b110000; // Go * 4
		end
	endcase
end
endmodule